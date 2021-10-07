package eam.edu.ingesoft.onlinestore.repositories

import eam.edu.ingesoft.onlinestore.model.City
import eam.edu.ingesoft.onlinestore.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        userRepository.create(User("1", "el_poblado", "santiago","Beltran_Florez", city))

        val user = entityManager.find(User::class.java, "1")
        Assertions.assertNotNull(user)
        Assertions.assertEquals("Santiago", user.name)
        Assertions.assertEquals("Beltran_Florez", user.lastName)
        Assertions.assertEquals("el_poblado", user.address)
        Assertions.assertEquals("1", user.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val city = City(1L, "medellin")
        entityManager.persist(city)
        userRepository.create(User("1", "el_poblado", "santiago","Beltran_Florez", city))

        //ejecutando...
        val user = entityManager.find(User::class.java, "1")
        user.name = "sebastian"
        user.lastName = "Beltran"
        user.address = "las_palmas"

        userRepository.update(user)

        //assersiones
        val userAssert = entityManager.find(User::class.java, "1")
        Assertions.assertEquals("sebastian", userAssert.name)
        Assertions.assertEquals("Beltran", userAssert.lastName)
        Assertions.assertEquals("las_palmas ", userAssert.address)
    }

    @Test
    fun findTest() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        userRepository.create(User("1", "el_poblado", "santiago","Beltran_Florez", city))

        val user = userRepository.find("1")

        Assertions.assertNotNull(user)
        Assertions.assertEquals("santiago", user?.name)
        Assertions.assertEquals("Beltran_Florez", user?.lastName)
        Assertions.assertEquals("el_poblado", user?.address)
    }

    @Test
    fun testDelete() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        userRepository.create(User("1", "el_poblado", "santiago","Beltran_Florez", city))

        userRepository.delete("1")

        val user = entityManager.find(User::class.java, "1")
        Assertions.assertNull(user)
    }
}