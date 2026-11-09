package com.royalpalace.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF07142E)
private val Royal = Color(0xFF1646A0)
private val Gold = Color(0xFFFFC93C)
private val Pale = Color(0xFFEAF0FF)
private val Panel = Color(0xFF10264B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RoyalPalaceApp() }
    }
}

@Composable
fun RoyalPalaceApp() {
    var selected by remember { mutableIntStateOf(0) }
    MaterialTheme(colorScheme = darkColorScheme(
        primary = Gold,
        secondary = Color(0xFF6CB6FF),
        background = Navy,
        surface = Panel
    )) {
        Scaffold(
            containerColor = Navy,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF0B1D3B)) {
                    val labels = listOf("Accueil", "Matchs", "Analyses", "Profil")
                    val icons = listOf(Icons.Default.Home, Icons.Default.SportsSoccer,
                        Icons.Default.Analytics, Icons.Default.EmojiEvents)
                    labels.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            when (selected) {
                0 -> HomeScreen(Modifier.padding(padding))
                1 -> MatchesScreen(Modifier.padding(padding))
                2 -> AnalysisScreen(Modifier.padding(padding))
                else -> ProfileScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize().background(Navy),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("♛", color = Gold, fontSize = 36.sp)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("ROYAL PALACE", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Black)
                    Text("SPORTS INTELLIGENCE", color = Gold, fontSize = 10.sp, letterSpacing = 2.sp)
                }
            }
        }
        item {
            Box(
                Modifier.fillMaxWidth().background(
                    Brush.linearGradient(listOf(Royal, Color(0xFF09234D))),
                    RoundedCornerShape(22.dp)
                ).padding(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("WELCOME TO THE ARENA", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text("Analysez. Comprenez.\nDécidez avec méthode.", color = Color.White,
                        fontSize = 25.sp, fontWeight = FontWeight.Bold, lineHeight = 31.sp)
                    Text("Votre centre d'analyse sportive.", color = Pale, fontSize = 13.sp)
                }
            }
        }
        item { SectionTitle("Sports disponibles") }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SportTile("⚽", "Football", Modifier.weight(1f))
                SportTile("🏀", "Basketball", Modifier.weight(1f))
                SportTile("🎾", "Tennis", Modifier.weight(1f))
            }
        }
        item { SectionTitle("Matchs de démonstration") }
        item {
            MatchCard("PREMIER LEAGUE", "Arsenal", "Manchester City", "Demo")
        }
        item {
            MatchCard("LA LIGA", "Real Madrid", "Barcelona", "Demo")
        }
        item {
            Text(
                "Les rencontres et cotes affichées dans cette première version sont fictives. " +
                    "Les connexions aux fournisseurs de données seront ajoutées dans une prochaine étape.",
                color = Color(0xFFB7C5DF), fontSize = 11.sp, lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun MatchesScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionTitle("Centre des matchs")
        Text("La liste des matchs en direct et le calendrier seront connectés aux sources de données lors de l'étape 2.",
            color = Pale, fontSize = 14.sp, lineHeight = 21.sp)
        MatchCard("DEMO", "Arsenal", "Manchester City", "À venir")
        MatchCard("DEMO", "Real Madrid", "Barcelona", "À venir")
    }
}

@Composable
private fun AnalysisScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        SectionTitle("Moteur d'analyse")
        Text("Le projet contient une première base mathématique Poisson pour estimer des distributions de scores.",
            color = Pale, fontSize = 14.sp, lineHeight = 21.sp)
        InfoPanel("Modèle Poisson", "Distribution de buts à partir d'une estimation xG.")
        InfoPanel("Prochaines intégrations", "Dixon-Coles, Elo, Monte Carlo, xG/xGA et analyse de valeur.")
        InfoPanel("Données", "Aucune donnée sportive réelle n'est encore connectée.")
    }
}

@Composable
private fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        SectionTitle("Profil & paramètres")
        InfoPanel("ROYAL PALACE", "Version 0.1.0 — première version technique.")
        InfoPanel("Confidentialité", "Les clés API ne doivent jamais être placées dans l'application Android.")
        InfoPanel("Jeu responsable", "Les analyses sont probabilistes et ne garantissent aucun résultat. Ne misez jamais de l'argent que vous ne pouvez pas perdre.")
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
}

@Composable
private fun SportTile(emoji: String, name: String, modifier: Modifier = Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(16.dp)).padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji, fontSize = 26.sp)
        Spacer(Modifier.height(6.dp))
        Text(name, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun MatchCard(league: String, home: String, away: String, status: String) {
    Column(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(18.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(league, color = Color(0xFF9FB8E8), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Surface(color = Color(0xFF243D63), shape = RoundedCornerShape(20.dp)) {
                Text(status.uppercase(), color = Gold, fontSize = 9.sp,
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp))
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(home, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("VS", color = Gold, fontWeight = FontWeight.Black, fontSize = 13.sp)
            Text(away, color = Color.White, fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f), textAlign = TextAlign.End)
        }
        Text("Données fictives — aucune cote réelle", color = Color(0xFF9FB8E8), fontSize = 10.sp)
    }
}

@Composable
private fun InfoPanel(title: String, body: String) {
    Column(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(16.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, color = Gold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(body, color = Pale, fontSize = 12.sp, lineHeight = 18.sp)
    }
}
