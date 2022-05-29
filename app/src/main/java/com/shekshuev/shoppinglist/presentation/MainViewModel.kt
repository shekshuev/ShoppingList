package com.shekshuev.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shekshuev.shoppinglist.data.ShopListRepositoryImpl
import com.shekshuev.shoppinglist.domain.DeleteShopItemUseCase
import com.shekshuev.shoppinglist.domain.EditShopItemUseCase
import com.shekshuev.shoppinglist.domain.GetShopListUseCase
import com.shekshuev.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopListItem(shopItem: ShopItem) {
        deleteShopListUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(shopItem)
    }

}