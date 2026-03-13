package hsg.kurswahl.manager.dataClass

enum class Fs3Art {
    VIER_STUENDIG,  // Fall 1: FS3 4-stündig, kein WPU
    ZWEI_STUENDIG,  // Fall 2: FS3 2-stündig, ein WPU
    KEINE_FS3       // Fall 3: keine FS3, zwei WPUs
}
