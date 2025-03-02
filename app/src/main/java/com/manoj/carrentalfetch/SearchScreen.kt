package com.manoj.carrentalfetch

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun SearchInputBox(padding: PaddingValues) {
    var pickupLocation by remember { mutableStateOf("") }
    var dropOffLocation by remember { mutableStateOf("") }
    var pickupDate by remember { mutableStateOf("") }
    var dropOffDate by remember { mutableStateOf("") }
    var showData by remember { mutableStateOf(false) }
    var showPickupDialog by remember { mutableStateOf(false) }
    var showDropOffDialog by remember { mutableStateOf(false) }
    var showPickupLocationWarning by remember { mutableStateOf(false) }
    var showDropOffLocationWarning by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = pickupLocation,
            onValueChange = {
                pickupLocation = it
                showPickupLocationWarning = pickupLocation.isEmpty()
            },
            label = { Text(stringResource(R.string.pickup_location)) },
            modifier = Modifier.fillMaxWidth()
        )
        if (showPickupLocationWarning) {
            Text(
                text = stringResource(R.string.pickup_location_cannot_be_empty),
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = dropOffLocation,
            onValueChange = {
                dropOffLocation = it
                showDropOffLocationWarning = dropOffLocation.isEmpty()
            },
            label = { Text(stringResource(R.string.drop_off_location)) },
            modifier = Modifier.fillMaxWidth()
        )
        if (showDropOffLocationWarning && dropOffDate.isNotEmpty()) {
            Text(
                text = "Drop off location cannot be empty, since you have entered drop off date.!",
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            showPickupDialog = true
            showData = false
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Select Pickup Date")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showPickupDialog) {
            DatePickerDialog(onDismissRequest = { showPickupDialog = false },
                onDateSelected = { date ->
                    pickupDate = date
                    showPickupDialog = false
                    Log.e("pickupDate: ", "Selected date: $dropOffDate")
                })
        }
        if (pickupDate.isNotEmpty()) {
            Text(
                text = "Pickup date: $pickupDate",
                color = Color.Blue,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showData = false
                showDropOffDialog = true
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.select_drop_off_date))
        }
        if (showDropOffDialog) {
            DatePickerDialog(onDismissRequest = { showDropOffDialog = false },
                onDateSelected = { date ->
                    dropOffDate = date
                    Log.e("dropOffDate: ", "Selected date: $dropOffDate date:$date")
                    showDropOffDialog = false
                })
        }
        if (dropOffDate.isNotEmpty()) {
            Text(
                text = "Drop off date: $dropOffDate",
                color = Color.Blue,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showData = true
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        if (showData) {
            if (pickupDate.isEmpty() || pickupLocation.isEmpty()) {
                Toast.makeText(
                    context,
                    stringResource(R.string.pickup_details_are_mandatory),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (dropOffLocation.isNotEmpty() && dropOffDate.isEmpty()) {
                Toast.makeText(
                    context, stringResource(R.string.missing_drop_off_date), Toast.LENGTH_SHORT
                ).show()
            } else if (dropOffDate.isNotEmpty() && dropOffLocation.isEmpty()) {
                Toast.makeText(
                    context, stringResource(R.string.missing_drop_off_location), Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "${AppConstants.BASE_URL}$pickupLocation/$dropOffLocation/" + "$pickupDate/$dropOffDate"
                    )
                )
                context.startActivity(intent)
            }
            showData = false
        }
    }
}
