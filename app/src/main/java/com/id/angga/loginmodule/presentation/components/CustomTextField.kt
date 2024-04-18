package com.id.angga.loginmodule.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.angga.loginmodule.presentation.UiText
import com.id.angga.loginmodule.presentation.ui.theme.TextFieldColor
import com.id.angga.loginmodule.presentation.ui.theme.TextFieldInputColor
import com.id.angga.loginmodule.presentation.ui.theme.TrailingIconColor
import com.id.angga.loginmodule.presentation.ui.theme.monserrat

@Composable
fun CustomTextField(
    value : String,
    labelText : String,
    onValueChange : (String) -> Unit,
    keyboardType: KeyboardType,
    trailingIcon : ImageVector,
    singleLine: Boolean = false,
    maxLine: Int = 1,
    errorMessage: UiText? = null,
    isError: Boolean = false,
) {
    var isPasswordShown by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(8.dp)
                ),
            value = value,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldColor,
                unfocusedContainerColor = TextFieldColor,
                disabledContainerColor = TextFieldColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            onValueChange = onValueChange,
            maxLines = maxLine,
            singleLine = singleLine,
            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
                    Icon(
                        modifier = Modifier.clickable {
                            isPasswordShown = !isPasswordShown
                        },
                        imageVector = trailingIcon,
                        tint = TrailingIconColor,
                        contentDescription = ""
                    )
                } else {
                    Icon(
                        imageVector = trailingIcon,
                        tint = TrailingIconColor,
                        contentDescription = ""
                    )
                }
            },
            label = {
                Text(
                    text = labelText,
                    fontFamily = monserrat,
                    color = TextFieldInputColor,
                    fontWeight = FontWeight.Medium
                )
            },

            visualTransformation = if (keyboardType == KeyboardType.Password) {
                if (isPasswordShown) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            } else {
                VisualTransformation.None
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
        )

        Text(
            text = if (isError) errorMessage?.asString(context) ?: "" else "",
            fontFamily = monserrat,
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}