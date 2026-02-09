package hsg.pruefungs.planer.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.platform.Font

val Montserrat = FontFamily(
    Font("fonts/Montserrat-Thin.ttf", FontWeight.Thin, FontStyle.Normal),
    Font("fonts/Montserrat-ThinItalic.ttf", FontWeight.Thin, FontStyle.Italic),
    Font("fonts/Montserrat-ExtraLight.ttf", FontWeight.ExtraLight, FontStyle.Normal),
    Font("fonts/Montserrat-ExtraLightItalic.ttf", FontWeight.ExtraLight, FontStyle.Italic),
    Font("fonts/Montserrat-Light.ttf", FontWeight.Light, FontStyle.Normal),
    Font("fonts/Montserrat-LightItalic.ttf", FontWeight.Light, FontStyle.Italic),
    Font("fonts/Montserrat-Regular.ttf", FontWeight.Normal, FontStyle.Normal),
    Font("fonts/Montserrat-Italic.ttf", FontWeight.Normal, FontStyle.Italic),
    Font("fonts/Montserrat-Medium.ttf", FontWeight.Medium, FontStyle.Normal),
    Font("fonts/Montserrat-MediumItalic.ttf", FontWeight.Medium, FontStyle.Italic),
    Font("fonts/Montserrat-SemiBold.ttf", FontWeight.SemiBold, FontStyle.Normal),
    Font("fonts/Montserrat-SemiBoldItalic.ttf", FontWeight.SemiBold, FontStyle.Italic),
    Font("fonts/Montserrat-Bold.ttf", FontWeight.Bold, FontStyle.Normal),
    Font("fonts/Montserrat-BoldItalic.ttf", FontWeight.Bold, FontStyle.Italic),
    Font("fonts/Montserrat-ExtraBold.ttf", FontWeight.ExtraBold, FontStyle.Normal),
    Font("fonts/Montserrat-ExtraBoldItalic.ttf", FontWeight.ExtraBold, FontStyle.Italic),
    Font("fonts/Montserrat-Black.ttf", FontWeight.Black, FontStyle.Normal),
    Font("fonts/Montserrat-BlackItalic.ttf", FontWeight.Black, FontStyle.Italic)
)

val OpenSans = FontFamily(
    Font("fonts/OpenSans-Bold.ttf", weight = FontWeight.Bold, style = FontStyle.Normal),
    Font("fonts/OpenSans-BoldItalic.ttf", weight = FontWeight.Bold, style = FontStyle.Italic),
    Font("fonts/OpenSans-ExtraBold.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font("fonts/OpenSans-ExtraBoldItalic.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font("fonts/OpenSans-Italic.ttf", weight = FontWeight.Normal, style = FontStyle.Italic),
    Font("fonts/OpenSans-Light.ttf", weight = FontWeight.Light, style = FontStyle.Normal),
    Font("fonts/OpenSans-LightItalic.ttf", weight = FontWeight.Light, style = FontStyle.Italic),
    Font("fonts/OpenSans-Medium.ttf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font("fonts/OpenSans-MediumItalic.ttf", weight = FontWeight.Medium, style = FontStyle.Italic),
    Font("fonts/OpenSans-Regular.ttf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font("fonts/OpenSans-SemiBold.ttf", weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font("fonts/OpenSans-SemiBoldItalic.ttf", weight = FontWeight.SemiBold, style = FontStyle.Italic),

    // Condensed Varianten
    Font("fonts/OpenSans_Condensed-Bold.ttf", weight = FontWeight.Bold, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-BoldItalic.ttf", weight = FontWeight.Bold, style = FontStyle.Italic),
    Font("fonts/OpenSans_Condensed-ExtraBold.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-ExtraBoldItalic.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font("fonts/OpenSans_Condensed-Italic.ttf", weight = FontWeight.Normal, style = FontStyle.Italic),
    Font("fonts/OpenSans_Condensed-Light.ttf", weight = FontWeight.Light, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-LightItalic.ttf", weight = FontWeight.Light, style = FontStyle.Italic),
    Font("fonts/OpenSans_Condensed-Medium.ttf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-MediumItalic.ttf", weight = FontWeight.Medium, style = FontStyle.Italic),
    Font("fonts/OpenSans_Condensed-Regular.ttf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-SemiBold.ttf", weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font("fonts/OpenSans_Condensed-SemiBoldItalic.ttf", weight = FontWeight.SemiBold, style = FontStyle.Italic),

    // SemiCondensed Varianten
    Font("fonts/OpenSans_SemiCondensed-Bold.ttf", weight = FontWeight.Bold, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-BoldItalic.ttf", weight = FontWeight.Bold, style = FontStyle.Italic),
    Font("fonts/OpenSans_SemiCondensed-ExtraBold.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-ExtraBoldItalic.ttf", weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font("fonts/OpenSans_SemiCondensed-Italic.ttf", weight = FontWeight.Normal, style = FontStyle.Italic),
    Font("fonts/OpenSans_SemiCondensed-Light.ttf", weight = FontWeight.Light, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-LightItalic.ttf", weight = FontWeight.Light, style = FontStyle.Italic),
    Font("fonts/OpenSans_SemiCondensed-Medium.ttf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-MediumItalic.ttf", weight = FontWeight.Medium, style = FontStyle.Italic),
    Font("fonts/OpenSans_SemiCondensed-Regular.ttf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-SemiBold.ttf", weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font("fonts/OpenSans_SemiCondensed-SemiBoldItalic.ttf", weight = FontWeight.SemiBold, style = FontStyle.Italic),
)

val displayFontFamily = Montserrat
val bodyFontFamily = OpenSans

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)
