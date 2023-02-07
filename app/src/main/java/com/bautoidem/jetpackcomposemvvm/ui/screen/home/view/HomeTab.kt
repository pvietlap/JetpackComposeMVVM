package com.bautoidem.jetpackcomposemvvm.ui.screen.home.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bautoidem.jetpackcomposemvvm.ui.theme.Black
import com.bautoidem.jetpackcomposemvvm.ui.theme.Color_Black_60
import com.bautoidem.jetpackcomposemvvm.ui.theme.Color_Purple_6EB
import com.bautoidem.jetpackcomposemvvm.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTab(
    modifier: Modifier,
    tabs: List<HomeTabData>,
    onTabClick: (HomeTabData) -> Unit,
    pagerState: PagerState
) {

    val scope = rememberCoroutineScope()
    val cornerShapeSize = 0.dp
    Surface(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .background(
                color = Color_Black_60, shape = RoundedCornerShape(
                    topEnd = cornerShapeSize,
                    topStart = cornerShapeSize,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .padding(PaddingValues(top = 1.dp)),
        shape = RoundedCornerShape(
            topEnd = cornerShapeSize,
            topStart = cornerShapeSize,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        elevation = 5.dp,
    ) {
        TabRow(
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .customTabIndicatorOffset(
                            currentTabPosition = tabPositions[pagerState.currentPage],
                            tabWidth = 70.dp
                        )
                        .background(
                            color = Color_Purple_6EB,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .clip(RoundedCornerShape(100.dp))
                        .padding(horizontal = 10.dp),
                    color = Color_Purple_6EB,
                    height = 1.dp
                )
            },
            modifier = modifier
                .background(
                    color = White, shape = RoundedCornerShape(
                        topEnd = cornerShapeSize,
                        topStart = cornerShapeSize,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topEnd = cornerShapeSize,
                        topStart = cornerShapeSize,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    )
                )
                .height(62.dp),
            selectedTabIndex = pagerState.currentPage, tabs = {
                tabs.forEachIndexed { tabIndex, tab ->
                    val selected = pagerState.currentPage == tabIndex
                    Tab(
                        modifier = Modifier.padding(horizontal = (if (tabIndex == 0 || tabIndex == tabs.size - 1) 10.dp else 0.dp)),
                        selected = pagerState.currentPage == tabIndex,
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(tabIndex)
                            }
                            onTabClick.invoke(tab)
                        },
                        content = {
                            val colorTintText = if (selected) Color_Purple_6EB else Black
                            val nameTag = stringResource(id = tab.nameResource ?: 0)
                            if (selected) {
                                Box(
                                    modifier = Modifier
                                        .padding()
                                        .background(
                                            color = Color_Purple_6EB,
                                            shape = CircleShape
                                        )
                                        .padding(PaddingValues(6.dp))
                                ) {
                                }
                            }
                            Text(
                                text = nameTag ?: tab.type.tabName,
                                style = MaterialTheme.typography.subtitle2.copy(
                                    color = colorTintText,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                            )
                        })
                }
            }, divider = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .height(1.dp)
                )
            })
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .clip(RoundedCornerShape(100.dp))
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset, y = (-57).dp)
        .width(currentTabWidth)
}