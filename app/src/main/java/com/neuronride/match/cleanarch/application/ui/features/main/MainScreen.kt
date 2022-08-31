package com.neuronride.match.cleanarch.application.ui.features.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neuronride.match.cleanarch.R.string
import com.neuronride.match.cleanarch.application.ui.features.main.viewmodel.CurrencyState
import com.neuronride.match.cleanarch.application.ui.features.main.viewmodel.MainViewModel
import com.neuronride.match.cleanarch.core.domain.entity.Currency

@Composable
fun MainScreen(viweModel: MainViewModel = viewModel()) {

    val liveStatus by viweModel.currenciesLiveData.observeAsState()

    liveStatus?.let { status ->
        when (status) {
            is CurrencyState.Error ->
                Toast.makeText(
                    LocalContext.current,
                    stringResource(string.currency_list_error),
                    Toast.LENGTH_LONG
                ).show()

            is CurrencyState.Loading ->
                Loading()

            is CurrencyState.ShowList ->
                ItemList(status.items)
        }
    }
}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ItemList(list: List<Currency>) {
    LazyColumn {
        itemsIndexed(list) { index, item ->
            ListItem(item, isLastItem = index == list.size - 1)
        }
    }
}

@Composable
private fun ListItem(item: Currency, isLastItem: Boolean = false) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        ItemLabel(stringResource(string.currency_item_name, item.name))
        ItemLabel(stringResource(string.currency_item_unit, item.unit))
        ItemLabel(stringResource(string.currency_item_type, item.type))
        ItemLabel(stringResource(string.currency_item_value, item.value.toString()))

        if (!isLastItem) {
            Divider(
                modifier = Modifier.padding(top = 16.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}

@Composable
private fun ItemLabel(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(24.dp)
            .padding(start = 16.dp, end = 16.dp),
        text = text
    )
}