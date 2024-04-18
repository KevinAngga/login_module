package com.id.angga.loginmodule.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.angga.loginmodule.presentation.ui.theme.BgChecked
import com.id.angga.loginmodule.presentation.ui.theme.TextButton
import com.id.angga.loginmodule.presentation.ui.theme.monserratSemiBold

@Composable
fun CustomButton(
    textButton : String,
    showIcon : Boolean,
    enable : Boolean = false,
    onClick : () -> Unit
) {
    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            contentColor = BgChecked,
            containerColor = BgChecked
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = textButton,
                fontSize = 20.sp,
                color = TextButton,
                style = monserratSemiBold
            )

            if (showIcon) {
                Icon(
                    modifier = Modifier.padding(top = 4.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    tint = TextButton,
                    contentDescription = "",
                )
            }
        }
    }
}