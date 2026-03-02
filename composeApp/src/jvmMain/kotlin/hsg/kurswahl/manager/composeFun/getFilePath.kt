package hsg.kurswahl.manager.composeFun

import java.awt.FileDialog
import java.awt.Frame
/**
 * @param mode true = save, false = load
 * @param type e.g. ".xlsx" or ".json" or ".pdf"
 * @param label Description of file to choose
 * @return FilePath or saving or loading
 */
fun getFilePath(mode: Boolean, type: String?, label: String, folderPath: Boolean = false): String {
    var filePath: String
    val fileDialog = FileDialog(
        null as Frame?,
        label,
        if(mode) { FileDialog.SAVE } else { FileDialog.LOAD }
    )
    fileDialog.file = type
    fileDialog.isVisible = true
    filePath = if (fileDialog.file != null) {
        if (folderPath) fileDialog.directory else fileDialog.directory + fileDialog.file
    } else {
        ""
    }
    return filePath
}