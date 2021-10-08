package eam.edu.ingesoft.onlinestore.services

import eam.edu.ingesoft.onlinestore.exceptions.BusinessException
import eam.edu.ingesoft.onlinestore.model.City
import eam.edu.ingesoft.onlinestore.model.Store
import eam.edu.ingesoft.onlinestore.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional

class StoreServiceTest {
    @Autowired
    lateinit var storeService: StoreService

    @Autowired
    lateinit var entityManager: EntityManager
    @Test
    fun testCreate() {
        val city = City(1L, "medellin")
        entityManager.persist(city)
        entityManager.persist(Store("1", "el_poblado", "tienda_medallo",city))

        try {
            storeService.createStore(Store("1", "el_poblado", "tienda_medallo",city))
            Assertions.fail()
        }catch (e: BusinessException){
            Assertions.assertEquals("This store already exists", e.message)

        }
    }
    @Test
    fun testEditUser(){
        val city = City(1L, "medellin")
        entityManager.persist(city)
        val store=Store("1", "el_poblado", "tienda_medallo",city)

        try {
            storeService.editStore(store)
            Assertions.fail()
        }
        catch (e:BusinessException){
            Assertions.assertEquals("This store does not exists", e.message)
        }

    }

    }

