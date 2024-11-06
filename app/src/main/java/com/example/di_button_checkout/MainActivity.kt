package com.example.di_button_checkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.di_button_checkout.ui.theme.DI_Button_CheckoutTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_Button_CheckoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Botones_Checkout() {
    // Variable para controlar si el texto está visible o no
    var showText by remember { mutableStateOf(false) }
    // Variable para controlar el CircularProgressIndicator
    var progressVisible by remember { mutableStateOf(false) }
    // Variable par el texto del boton
    var buttonText by remember { mutableStateOf("Presionar") }
    // Variable para habilitar y deshabilitar el boton
    var isButtonEnabled by remember { mutableStateOf(true) }
    // Variable que controla si el Switch está activado o no y por lo tanto si los RadioButtons son visibles
    var switchState by remember { mutableStateOf(false) }
    // Variable para indicar la imagen seleccionada
    var selectedImage by remember { mutableStateOf(1) }
    // Variable que define qué opción de RadioButton está seleccionada
    var selectedOption by remember { mutableStateOf("Opción 1") }
    // Lista de imágenes
    val images = listOf(
        painterResource(id = R.drawable.morado),
        painterResource(id = R.drawable.naranja),
        painterResource(id = R.drawable.verde)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono permanente
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Icono",
            modifier = Modifier.size(64.dp),
            tint = Color.Magenta
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Botón que cambia el texto y se deshabilita y se muestra el CircularProgressIndicator
        Button(
            onClick = {
                buttonText = "Botón presionado"
                progressVisible = true
                // Deshabilitamos el botón
                isButtonEnabled = false
            },
            // Habilitamos el botón
            enabled = isButtonEnabled
        ) {
            Text(buttonText)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Indicador de progreso que aparece durante 5 segundos
        if (progressVisible) {
            LaunchedEffect(Unit) {
                // Espera 5 segundos
                delay(5000)
                progressVisible = false
                buttonText = "Presionar"
                // Habilita el botón
                isButtonEnabled = true
            }
            // Muestramos CircularProgressIndicator()
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Checkbox(
            checked = showText,
            onCheckedChange = { showText = it },
            modifier = Modifier.padding(8.dp)
        )
        Text("Activar")
        // Si el checkbox está marcado, se muestra el texto con la opción seleccionada
        if (showText) {
            BasicText(text = "Opción seleccionada: $selectedOption")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Switch para controlar la visibilidad del grupo de RadioButtons
        Switch(
            checked = switchState,
            onCheckedChange = { switchState = it }
        )
        Text("Mostrar opciones")
        Spacer(modifier = Modifier.height(16.dp))
        // Si el Switch está activado, se muestra el grupo de RadioButtons
        if (switchState) {
            Column {
                Text("Selecciona una opción:")
                // Llama a la función que dibuja el grupo de RadioButtons
                RadioButtonGroup(
                    selectedOption = selectedOption,
                    onOptionSelected = { option ->
                        selectedOption = option
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Imagen que cambia cada vez que se pulsa un botón
        Image(
            painter = images[selectedImage - 1],
            contentDescription = "Imagen cambiante",
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Botón que cambia la imagen
        Button(onClick = {
            selectedImage = if (selectedImage < 3) selectedImage + 1 else 1
        }) {
            Text("Cambiar Imagen")
        }
    }
}
@Composable
fun RadioButtonGroup(selectedOption: String, onOptionSelected: (String) -> Unit) {
    // Lista de opciones para los RadioButtons
    val options = listOf("Opción 1", "Opción 2", "Opción 3")
    Column {
        // Recorre cada opción y crea un RadioButton para cada una
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                // RadioButton que se selecciona cuando coincide con la opción seleccionada
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) }
                )
                Text(text = option)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Botones_Checkout()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DI_Button_CheckoutTheme {
        Greeting("Android")
    }
}