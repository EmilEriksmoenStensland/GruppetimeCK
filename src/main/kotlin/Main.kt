import kotlinx.coroutines.*

private val myCoords = arrayOf (
    arrayOf(1, 2),
    arrayOf(3, 4),
    arrayOf(10, 40),
    arrayOf(69, -19),
    arrayOf(1, 2)
)

fun main() {
    runBlocking {
        val startTime = System.nanoTime()
        val allTemps = getAllCityWeatherWrong()
        val totalTime = System.nanoTime() - startTime

        println("Time: ${totalTime/1_000_000_000} seconds")
        println(allTemps.toString())
    }
}

suspend fun getAllCityWeatherWrong(): MutableList<Int> {
    val cityTemperatures = mutableListOf<Int>()
    for (e in myCoords) {
        val temp = getCityWeather(e)
        cityTemperatures.add(temp)
    }
    return cityTemperatures
}

suspend fun getCityWeather(e: Array<Int>): Int {
    delay(1000)
    return e[0]
}




suspend fun getAllCityWeather(): List<Int> {
    val cityTemperatures = mutableListOf<Deferred<Int>>()
        for (e in myCoords) {
            val temp = CoroutineScope(Dispatchers.IO).async {
                getCityWeather(e)
            }
            cityTemperatures.add(temp)
        }
    return cityTemperatures.awaitAll()
}


