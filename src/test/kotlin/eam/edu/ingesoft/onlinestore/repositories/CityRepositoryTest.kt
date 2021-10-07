package eam.edu.ingesoft.onlinestore.repositories

import eam.edu.ingesoft.onlinestore.model.City
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class CityRepositoryTest {

    @Autowired
    lateinit var cityRepository: CityRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        cityRepository.create(City(1L, "medellin"))

        val city = entityManager.find(City::class.java,1L)
        Assertions.assertNotNull(city)
        Assertions.assertEquals("medellin", city.name)
        Assertions.assertEquals(1L, city.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(City(1L, "medellin"))

        //ejecutando...
        val city = entityManager.find(City::class.java, 1L)
        city.name = "armenia"

        cityRepository.update(city)

        //assersiones
        val cityAssert = entityManager.find(City::class.java, 1L)
        Assertions.assertEquals("armenia", cityAssert.name)
    }

    @Test
    fun findTest() {
        entityManager.persist(City(1L, "medellin"))

        val city = cityRepository.find(1L)

        Assertions.assertNotNull(city)
        Assertions.assertEquals("medellin", city?.name)
    }

    @Test
    fun testDelete() {
        entityManager.persist(City(1L, "medellin"))
        cityRepository.delete(1L)

        val city = entityManager.find(City::class.java, 1L)
        Assertions.assertNull(city)
    }
}