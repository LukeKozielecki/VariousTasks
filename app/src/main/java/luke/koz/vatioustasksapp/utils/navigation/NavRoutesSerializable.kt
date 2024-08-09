package luke.koz.vatioustasksapp.utils.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutesSerializable {

    @Serializable
    object DummyTask1

    @Serializable
    object DummyTask2

    @Serializable
    object DummyTask3
}