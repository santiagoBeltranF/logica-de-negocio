package eam.edu.ingesoft.onlinestore.services

import eam.edu.ingesoft.onlinestore.exceptions.BusinessException
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

class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var entityManager: EntityManager
    @Test
    fun testCreate() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        entityManager.persist(User("1", "el_poblado", "santiago", "Beltran_Florez", city))

        try {
            userService.createUser(User("1", "el_poblado", "santiago", "Beltran_Florez", city))
            Assertions.fail()
        }catch (e:BusinessException){
            Assertions.assertEquals("This person already exists", e.message)

        }
    }
    @Test
    fun testEditUser(){
        val city = City(1L, "medellin")
        entityManager.persist(city)
        val user=User("1", "el_poblado", "santiago", "Beltran_Florez", city)

        try {
            userService.editUser(user)
            Assertions.fail()
        }
        catch (e:BusinessException){
            Assertions.assertEquals("This user does not exist", e.message)
        }

    }
}