package eam.edu.ingesoft.onlinestore.services

import eam.edu.ingesoft.onlinestore.exceptions.BusinessException
import eam.edu.ingesoft.onlinestore.model.Product
import eam.edu.ingesoft.onlinestore.model.User

import eam.edu.ingesoft.onlinestore.repositories.ProductRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var entityManager: EntityManager


    fun createProduct(product: Product) {
        val productById = productRepository.find(product.id)

        if(productById != null){
            throw BusinessException("This Product already exists")
        }

        val productByNamE = productRepository.findName(product.name)

        if(productByNamE != null){
            throw BusinessException("This Product already exists")
        }

        productRepository.create(product)
        }


    fun editProduct(product: Product){
        val productById = productRepository.find(product.id)

        if (productById == null) {
            throw BusinessException("This product does not exist")
        }

        val productByName= productRepository.findName(product.name)

        if(productByName!=null){
            throw BusinessException("This product with this name already exists")
        }

        productRepository.update(product)
    }

    }

