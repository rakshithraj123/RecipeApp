package com.app.debugmyapp.ui.multicall

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.debugmyapp.R
import com.app.debugmyapp.ui.multicall.ui.theme.DebugMyAppTheme
import com.app.debugmyapp.ui.recipe.categorylist.MainViewModel

@Composable
fun RegistrationScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onRegistrationSuccess: () -> Unit,
) {
    Column(modifier = modifier
        .background(Color.White)
        .verticalScroll(rememberScrollState(), enabled = true)) {
        RegistrationHeader()
        RegistrationDescription(modifier = Modifier.padding(top = 10.dp))
        RegisterForm(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp))

        Button(onClick = { onRegistrationSuccess()} ,modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Register")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(modifier: Modifier = Modifier) {

//    var name by rememberSaveable { mutableStateOf(TextFieldValue("")) }
//    var email by rememberSaveable { mutableStateOf(TextFieldValue("")) }
//    var phone by rememberSaveable { mutableStateOf(TextFieldValue("")) }

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                colorResource(id = R.color.registration_form),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(all = 10.dp)
    ) {

        RegisterField(modifier = Modifier.align(Alignment.CenterHorizontally),
            hint = stringResource(id = R.string.name),
            icon = painterResource(id = R.drawable.user),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences,
            ),
            keyboardActions = KeyboardActions(),
            value = name,
            onValueChange = {
                name = it
            })

        Spacer(modifier = Modifier.height(15.dp))

        RegisterField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            hint = stringResource(id = R.string.email),
            icon = painterResource(id = R.drawable.email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences,
            ),
            keyboardActions = KeyboardActions(
            ),
            value = email,
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        val keyboardController = LocalSoftwareKeyboardController.current

        RegisterField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            hint = stringResource(id = R.string.mobile),
            icon = painterResource(id = R.drawable.mobile),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            value = phone,
            onValueChange = {
                phone = it
            }
        )
    }
}

@Composable
fun RegisterField(
    modifier: Modifier = Modifier,
    hint: String,
    icon: Painter,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,

) {
    Row(
        modifier = modifier
            .shadow(elevation = 4.dp)
            .background(colorResource(id = R.color.white))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Image(
            painter = icon, contentDescription = "user",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(40.dp)
                .width(40.dp)
                .background(colorResource(id = R.color.registration_form))
                .padding(10.dp)
        )

        RegisterTextField(
            modifier = Modifier.align(Alignment.CenterVertically),
            value = value,
            onValueChange = onValueChange,
            hint = hint,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }
}

@Composable
fun RegisterTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    BasicTextField(
        value = value,
        singleLine = true,
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .wrapContentHeight()
                    .background(Color.White)
                    .fillMaxWidth()
            ) {

                if (value.isEmpty()) {
                    HintText(hint)
                }
                innerTextField()
            }
        },
        modifier = modifier
            .height(40.dp)
            .padding(start = 5.dp)
            .clickable(onClick = { }),

        )
}


@Composable
fun HintText(hintText: String) {
    Text(
        text = hintText,
        style = TextStyle(
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
        ),
    )
}

@Composable
fun RegistrationDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "Let\\'s get you started",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "by verifying your credentials",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun RegistrationHeader(
    modifier: Modifier = Modifier,
    bannerPadding: Dp = dimensionResource(id = R.dimen.ten),
    bannerImage: Painter = painterResource(id = R.drawable.banner),
    toolbarBackgroundColor: Color = colorResource(id = R.color.toolbar_bg)
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (banner, column) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .constrainAs(column) {
                    top.linkTo(banner.top)
                    bottom.linkTo(banner.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(toolbarBackgroundColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White)
            )

        }

        // Banner Image
        Image(
            painter = bannerImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = bannerPadding, bottom = bannerPadding)
                .constrainAs(banner) {
                    centerTo(parent)
                }

        )

    }

}

//@Composable
//fun RegistrationHeader(
//    bannerPadding: Dp = dimensionResource(id = R.dimen.ten),
//    bannerImage: Painter = painterResource(id = R.drawable.banner),
//    toolbarBackgroundColor: Color = colorResource(id = R.color.toolbar_bg)
//) {
//
//    // State to store the image height
//    var imageHeight by remember { mutableStateOf(0.dp) }
//    // Using LocalDensity to convert px to dp within Composable context
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(imageHeight)
//        ) {
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .background(toolbarBackgroundColor)
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .background(Color.White)
//            )
//
//        }
//        // Banner Image
//        Image(
//            painter = bannerImage,
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = bannerPadding, bottom = bannerPadding)
//                .align(Alignment.Center)
//                .onGloballyPositioned { coordinates ->
//                    imageHeight =  with(density) { coordinates.size.height.toDp() + bannerPadding + bannerPadding } // Measure the height of the Image
//                }
//        )
//
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DebugMyAppTheme {
        RegisterForm()
    }
}