package dev.reimer.alexa

import dev.reimer.domain.ktx.Domain
import dev.reimer.kotlin.jvm.ktx.iterator
import dev.reimer.kotlin.jvm.ktx.zipped
import dev.reimer.wayback.api.WaybackApi
import java.io.File
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.logging.Level
import java.util.logging.Logger

internal class AlexaTopRanksProvider(
    private val timestamp: LocalDateTime,
    cacheDir: File
) {

    private companion object {
        private val TIMESTAMP_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd")

        private val API = WaybackApi()
        val TOP_1M_URL = URL("http://s3.amazonaws.com/alexa-static/top-1m.csv.zip")

        private val log: Logger = Logger.getLogger("AlexaFile").apply {
            level = Level.WARNING
        }
    }

    private val timestampString: String = TIMESTAMP_PATTERN.format(timestamp)
    private val archive = cacheDir.resolve("top-1m-$timestampString.csv.zip")
    private val file = cacheDir.resolve("top-1m-$timestampString.csv")
    private val zipFile = TOP_1M_URL.path.substringAfterLast('/').removeSuffix(".zip")

    suspend fun get(): Map<Domain, Long> {
        if (!file.exists() || !file.isFile) {
            unzipArchive()
        }
        return AlexaRanksFileCache(file)
    }

    private suspend fun unzipArchive() {
        if (!archive.exists() || !archive.isFile) {
            downloadArchive()
        }

        log.info("Unzipping alexa file.")

        val zipStream = archive.inputStream().buffered().zipped()
        zipStream.use { zis ->
            for (entry in zis) {
                if (entry.name == zipFile) {
                    log.fine("Found alexa file ZIP entry.")
                    file.outputStream().buffered().use {
                        zis.copyTo(it)
                    }
                    break
                }
            }
        }
    }

    private suspend fun downloadArchive() {
        log.info("Downloading alexa file archive.")

        val snapshot = API.available(TOP_1M_URL, timestamp)
            .archivedSnapshots
            .closest
        requireNotNull(snapshot) {
            "Could not find Alexa top 1M snapshot near timestamp $timestamp."
        }
        snapshot.downloadTo(archive)
    }
}