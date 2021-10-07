package eam.edu.ingesoft.onlinestore.repositories

import eam.edu.ingesoft.onlinestore.model.City
import eam.edu.ingesoft.onlinestore.model.Store
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class StoreRepositoryTest {
    @Autowired
    lateinit var storeRepository: StoreRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        storeRepository.create(Store("1", "el_poblado", "tienda_medallo", city))

        val store = entityManager.find(Store::class.java, "1")
        Assertions.assertNotNull(store)
        Assertions.assertEquals("tienda_medallo", store.name)
        Assertions.assertEquals("el_poblado", store.address)
        Assertions.assertEquals("1", store.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val city = City(1L, "medellin")
        entityManager.persist(city)
        entityManager.persist(Store("1", "el_poblado", "tienda_medallo",city))

        //ejecutando...
        val store = entityManager.find(Store::class.java, "1")
        store.name = "tienda_las_palmas"
        store.address = "las_palmas"

        storeRepository.update(store)

        //assersiones
        val storeAssert = entityManager.find(Store::class.java, "1")
        Assertions.assertEquals("tienda_las_palmas", storeAssert.name)
        Assertions.assertEquals("las_palmas", storeAssert.address)
    }

    @Test
    fun findTest() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        entityManager.persist(Store("1", "el_poblado", "tienda_medallo",city))

        val user = storeRepository.find("1")

        Assertions.assertNotNull(user)
        Assertions.assertEquals("tienda_medallo", user?.name)
        Assertions.assertEquals("el_poblado", user?.address)
    }

    @Test
    fun testDelete() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        entityManager.persist(Store("1", "el_poblado", "tienda_medallo", city))
        storeRepository.delete("1")

        val category = entityManager.find(Store::class.java, "1")
        Assertions.assertNull(category)
    }
}