package eam.edu.ingesoft.onlinestore.repositories

import eam.edu.ingesoft.onlinestore.model.Category
import eam.edu.ingesoft.onlinestore.model.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val category = Category(1L,"carnes_Frias")
        entityManager.persist(category)
        productRepository.create(Product(1L, "salchichon","carne",category))

        val product = entityManager.find(Product::class.java,1L)
        Assertions.assertNotNull(product)
        Assertions.assertEquals("salchichon", product.name)
        Assertions.assertEquals(1L, product.id)
    }

    @Test
    fun testUpdate() {
        val category = Category(1L,"carnes_Frias")
        entityManager.persist(category)
        entityManager.persist(Product(1L, "salchichon","carne",category))

        //ejecutando...
        val product = entityManager.find(Product::class.java, 1L)
        product.name = "mortadela"

        productRepository.update(product)

        //assersiones
        val productAssert = entityManager.find(Product::class.java, 1L)
        Assertions.assertEquals("mortadela", productAssert.name)
    }

    @Test
    fun findTest() {
        val category = Category(1L,"carnes_Frias")
        entityManager.persist(category)
        entityManager.persist(Product(1L, "salchichon","carne",category))

        val prodruct = productRepository.find(1L)

        Assertions.assertNotNull(prodruct)
        Assertions.assertEquals("salchichon", prodruct?.name)
    }

    @Test
    fun testDelete() {
        val category = Category(1L,"carnes_Frias")
        entityManager.persist(category)
        entityManager.persist(Product(1L, "salchichon","carne",category))
        productRepository.delete(1L)

        val product = entityManager.find(Product::class.java, 1L)
        Assertions.assertNull(product)
    }

}