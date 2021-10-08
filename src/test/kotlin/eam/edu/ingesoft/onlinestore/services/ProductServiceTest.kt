package eam.edu.ingesoft.onlinestore.services

import eam.edu.ingesoft.onlinestore.exceptions.BusinessException
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
class ProductServiceTest {
    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateProducto() {
        val category = Category(1L, "carnes_Frias")
        entityManager.persist(category)
        entityManager.persist(Product(1L, "salchichon", "carne", category))

        try {
            productService.createProduct(Product(1L, "salchichon", "carne", category))
            Assertions.fail()
        } catch (e: BusinessException) {
            Assertions.assertEquals("This Product already exists", e.message)

        }


    }
    @Test
    fun testProductEdit() {
        val category = Category(1L,"carnes_Frias")
        entityManager.persist(category)

        val product = Product(1L,"salchichon","carne",category)

        try{
            productService.editProduct(product)
            Assertions.fail()

        } catch (e:BusinessException){
            Assertions.assertEquals("This product does not exist", e.message)
        }
    }


}