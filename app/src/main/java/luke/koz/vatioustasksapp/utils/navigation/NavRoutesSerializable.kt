package luke.koz.vatioustasksapp.utils.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutesSerializable {

    @Serializable
    object HubScreen

    @Serializable
    object ApproximationsOfPi

    @Serializable
    object DummyTask2

    @Serializable
    object DummyTask3
}