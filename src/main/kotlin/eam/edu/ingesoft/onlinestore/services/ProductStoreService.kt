package eam.edu.ingesoft.onlinestore.services

import eam.edu.ingesoft.onlinestore.exceptions.BusinessException
import eam.edu.ingesoft.onlinestore.model.ProductStore
import eam.edu.ingesoft.onlinestore.repositories.ProductStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class ProductStoreService {
    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var productStoreRepository: ProductStoreRepository



    fun createProductStore(productStore: ProductStore) {
        val productById = productStoreRepository.findByProduct(productStore.id)

        if (productById.size != 0) {
            throw BusinessException("This product already exists on the store")
        }
        productStoreRepository.create(productStore)
    }

    fun  decreaseStockProductStore(productStore: ProductStore) {
        val productStore = entityManager.find(ProductStore::class.java, productStore?.id)
        if (productStore.stock > 0){
            productStore.stock = productStore.stock - 1
            productStoreRepository.update(productStore)
        }
        else{
            throw BusinessException("There is not stock")
        }
    }

    fun  increaseStockProductStore(productStore: ProductStore) {
        val productStore = entityManager.find(ProductStore::class.java, productStore?.id)
        if (productStore.stock >= 0){
            productStore.stock = productStore.stock + 1
            productStoreRepository.update(productStore)
        }
        else{
            throw BusinessException("There is not stock")
        }
    }



}