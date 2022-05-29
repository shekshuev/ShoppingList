package com.shekshuev.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shekshuev.shoppinglist.domain.ShopItem
import com.shekshuev.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId ++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopList.remove(getShopItem(shopItem.id))
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem = shopList.find {
        it.id == shopItemId
    } ?: throw RuntimeException("Element with id=$shopItemId not found!")

    override fun getShopList(): LiveData<List<ShopItem>> = shopListLD

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }

}