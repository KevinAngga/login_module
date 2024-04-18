package com.id.angga.loginmodule.presentation.onboarding

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.ui.theme.BgChecked
import com.id.angga.loginmodule.presentation.ui.theme.IndicatorSelected
import com.id.angga.loginmodule.presentation.ui.theme.TextBlack
import com.id.angga.loginmodule.presentation.ui.theme.monserratBold
import com.id.angga.loginmodule.presentation.ui.theme.monserratLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingViewModel : OnBoardingViewModel = hiltViewModel(),
    navigateToLogin : () -> Unit
) {
    val onboaring = listOf<OnBoarding>(
        OnBoarding(
            R.drawable.on_board_one,
            R.string.on_boarding_title_one,
            R.string.on_boarding_sub_title_one
        ),

        OnBoarding(
            R.drawable.on_board_two,
            R.string.on_boarding_title_two,
            R.string.on_boarding_sub_title_two
        ),

        OnBoarding(
            R.drawable.on_board_three,
            R.string.on_boarding_title_three,
            R.string.on_boarding_sub_title_three
        )
    )

    val pagerState = rememberPagerState {
        onboaring.size
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

       HorizontalPager(
           state = pagerState
       ) {currentPage ->
          Column(
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.Start
          ) {
              Column(
                  modifier = Modifier.fillMaxWidth(),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  Image(
                      painter = painterResource(id = onboaring[currentPage].imageRes),
                      contentDescription = null
                  )
              }

              Spacer(modifier = Modifier.height(90.dp))


              Text(
                  text = stringResource(id = onboaring[currentPage].titleRes),
                  fontSize = 36.sp,
                  style = monserratBold
              )

              Text(
                  text = stringResource(id = onboaring[currentPage].descRes),
                  fontSize = 24.sp,
                  style = monserratLight
              )
          }
       }


        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            PageIndicator(
                pageCount = onboaring.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
            )

            IconButton(
                onClick = {
                  scope.launch {
                      if (pagerState.currentPage == pagerState.pageCount - 1) {
                          onBoardingViewModel.saveOnBoardingStatus()
                          navigateToLogin()
                      } else {
                          pagerState.animateScrollToPage(
                              page = pagerState.currentPage + 1
                          )
                      }
                  }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = TextBlack
                )
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    tint = Color.White,
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = ""
                )
            }

        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage, modifier = modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected : Boolean, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .height(6.dp)
            .width(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) IndicatorSelected else BgChecked)
    )
}



@Composable
@Preview
fun OnBoardingScreenPreview() {
    OnBoardingScreen(
        navigateToLogin = {}
    )
}


data class OnBoarding(
    val imageRes : Int,
    val titleRes : Int,
    val descRes : Int
)