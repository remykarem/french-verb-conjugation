import java.lang.RuntimeException

enum class Type {
    ER, OIRE, CRIRE, IRE, PRENDRE, INDRE, DRE, AITRE, TRE, TIR, ENIR, CEVOIR, IR, IVRE;

    companion object {
        fun from(infinitive: String): Type {
            return with(infinitive) {
                when {
                    // ER
                    endsWith("er") -> ER
                    // IRE
                    endsWith("oire") -> OIRE
                    endsWith("crire") -> CRIRE
                    endsWith("ire") -> IRE
                    // DRE
                    endsWith("prendre") -> PRENDRE
                    endsWith("indre") -> INDRE
                    endsWith("dre") -> DRE
                    // TRE
                    endsWith("aître") -> AITRE
                    endsWith("tre") -> TRE
                    // IR
                    endsWith("tir") -> TIR
                    endsWith("enir") -> ENIR
                    endsWith("cevoir") -> CEVOIR
                    endsWith("ir") -> IR
                    // VRE
                    endsWith("ivre") -> IVRE
                    else -> throw RuntimeException("Unknown verb. Kindly feedback this.")
                }
            }
        }
    }

    fun getStemFrom(infinitive: String): String {
        val stemEnd: Int = when (this) {
            ER -> 2
            OIRE -> 4
            CRIRE -> 2
            IRE -> 2
            PRENDRE -> 5
            INDRE -> 4
            DRE -> 2
            AITRE -> 5
            TRE -> 3
            TIR -> 3
            ENIR -> 4
            CEVOIR -> 5
            IR -> 1
            IVRE -> 3
        }
        return infinitive.substring(0, infinitive.length - stemEnd)
    }
}

// General
private val PRONOUNS = listOf(0, 1, 2, 3, 4, 5)
private val REFLEXIVES = arrayOf("me", "te", "se", "nous", "vous", "se")

// Present
private val PRESENT_SUFFIXES = hashMapOf(
    Type.ER to listOf("e", "es", "e", "ons", "ez", "ent"),
    Type.DRE to listOf("s", "s", "", "ons", "ez", "ent"),
    Type.TRE to listOf("s", "s", "e", "tons", "tez", "tent"),
    Type.TIR to listOf("s", "s", "t", "tons", "tez", "tent"),
    Type.IVRE to listOf("s", "s", "t", "vons", "vez", "vent"),
    Type.CRIRE to listOf("s", "s", "t", "vons", "vez", "vent"),
    Type.IR to listOf("s", "s", "t", "ssons", "ssez", "ssent"),
    Type.IRE to listOf("s", "s", "t", "ssons", "ssez", "ssent"),
    Type.INDRE to listOf("ns", "ns", "nt", "gnons", "gnez", "gnent"),
    Type.OIRE to listOf("ois", "ois", "oit", "oyons", "oyez", "oient"),
    Type.CEVOIR to listOf("ois", "ois", "oit", "evons", "evez", "oivent"),
    Type.ENIR to listOf("iens", "iens", "ient", "enons", "enez", "iennent"),
    Type.PRENDRE to listOf("ends", "ends", "end", "enons", "enez", "ennent"),
    Type.AITRE to listOf("ais", "ais", "aît", "aissons", "aissez", "aissent"),
)
private val PRESENT_EXCEPTIONS = hashMapOf(
    "avoir" to listOf("ai", "as", "a", "avons", "avez", "ont"),
    "être" to listOf("suis", "es", "est", "sommes", "êtes", "sont"),
    "dire" to listOf("dis", "dis", "dit", "disons", "dites", "disent"),
    "lire" to listOf("lis", "lis", "lit", "lisons", "lisez", "lisent"),
    "voir" to listOf("vois", "vois", "voit", "voyons", "voyez", "voient"),
    "faire" to listOf("fais", "fais", "fait", "faisons", "faites", "font"),  // TODO se faire?
    "boire" to listOf("bois", "bois", "boit", "buvons", "buvez", "boivent"),
    "savoir" to listOf("sais", "sais", "sait", "savons", "savez", "savent"),
    "vouloir" to listOf("veux", "veux", "veut", "voulons", "voulez", "veulent"),
)

// Past
private val PAST_SUFFIXES = hashMapOf(
    Type.ER to "é",
    Type.OIRE to "u",
    Type.CRIRE to "t",
    Type.IRE to "t", // ""
    Type.PRENDRE to "is", // TODO or u (couru)
    Type.INDRE to "nt", // TODO or u (couru)
    Type.DRE to "u", // TODO s t
    Type.AITRE to "u",
    Type.TRE to "is",
    Type.TIR to "ti",
    Type.ENIR to "enu",
    Type.CEVOIR to "u",
    Type.IR to "t", // TODO or u (couru)
    Type.IVRE to "vi", // TODO or u (couru)
)
private val VERBS_OF_MOTION = hashSetOf(
    "aller", "arriver", "descendre", "redescendre", "entrer", "rentrer", "monter", "remonter", "mourir", "naître",
    "renaître", "partir", "repartir", "passer", "rester", "retourner", "sortir", "ressortir", "tomber", "retomber",
    "venir", "devenir", "parvenir", "revenir"
)
private val PAST_EXCEPTIONS = hashMapOf(
    "voir" to "vu",
    "être" to "été",
    "avoir" to "eu",
    "savoir" to "su",
    "vouloir" to "voulu"
)
private val SUFFIX = arrayOf("(e)", "(e)", "(e)", "(e)s", "(e)s", "(e)s")

// Imperatives
private val IMPERATIVE_EXCEPTIONS = hashMapOf(
    "être" to "soyez",
    "avoir" to "Ayez"
)
private val IMPERATIVE_REFLEXIVES = arrayOf("", "toi", "", "", "vous", "")

enum class Pronoun {
    Je, Tu, Il, Nous, Vous, Ils;

    fun toInt(): Int {
        return when (this) {
            Je -> 0
            Tu -> 1
            Il -> 2
            Nous -> 3
            Vous -> 4
            Ils -> 5
        }
    }

    companion object {
        fun ordinals(): List<Int> = values().map { it.ordinal }
        fun imperativeOrdinals(): List<Int> = values().filter {
            it == Tu || it == Vous
        }.map { it.ordinal }
    }

}


class FrenchVerb(infinitive: String) {

    private val isReflexive = infinitive.startsWith("se ") || infinitive.startsWith("s'")
    private val infinitive: String = infinitive.replace("se ", "").replace("s'", "")
    private val type: Type = Type.from(this.infinitive)
    private val stem: String = type.getStemFrom(this.infinitive)  // exception verbs don't have a stem

    val present: List<String> by lazy {

        if (this.infinitive in PRESENT_EXCEPTIONS) {
            return@lazy PRESENT_EXCEPTIONS[this.infinitive]!!
        }

        val suffixToStem = PRESENT_SUFFIXES[type] ?: throw RuntimeException("Something went wrong")
        return@lazy PRONOUNS.map {
            if (isReflexive)
                postProcess("${REFLEXIVES[it]} $stem${suffixToStem[it]}")
            else
                postProcess(stem + suffixToStem[it])
        }
    }

    val past: List<String> by lazy {

        val suffixToStem = PAST_SUFFIXES[type] ?: throw RuntimeException("Something went wrong")
        val isVerbOfMotion = this.infinitive in VERBS_OF_MOTION
        val isException = this.infinitive in PAST_EXCEPTIONS

        Pronoun.ordinals().map {
            postProcess(
                when {

                    isVerbOfMotion -> "${PRESENT_EXCEPTIONS["être"]!![it]} $stem$suffixToStem${SUFFIX[it]}"

                    isReflexive -> "${REFLEXIVES[it]} ${PRESENT_EXCEPTIONS["être"]!![it]} $stem${PAST_SUFFIXES[type]}"

                    isException -> "$stem-${REFLEXIVES[it]}"

                    else -> "$stem${PAST_SUFFIXES[type]}"
                }
            )
        }
    }


    val imperative: List<String> by lazy {

        val suffix = PRESENT_SUFFIXES[type] ?: throw RuntimeException("Something went wrong")
        val isException = this.infinitive in PRESENT_EXCEPTIONS

        Pronoun.imperativeOrdinals().map {

            val reflexive = IMPERATIVE_REFLEXIVES[it]
            val suffixToStem = suffix[it]

            postProcess(
                when {
                    isException -> PRESENT_EXCEPTIONS[this.infinitive]!![it]

                    type == Type.ER && it == 1 && isReflexive -> "$stem${suffix[2]}-$reflexive"

                    type == Type.ER && it == 1 -> "$stem${suffix[2]}"

                    isReflexive -> "$stem$suffixToStem-$reflexive"

                    else -> "$stem$suffixToStem"
                }
            )
        }
    }


    private fun postProcess(wordsa: String): String {

        // Cedilla eg. placons -> plaçons
        var words = wordsa
        if (stem.endsWith("c")) words = words.replaceFirst("([\\w\\s]+)c(o[\\w\\s]+)".toRegex(), "$1ç$2")

        // Connect pronoun with verb (vowel)
        words = words.replaceFirst("(J)e\\s([aeéiou].+)".toRegex(), "$1'$2")

        // Connect reflexive with verb OR avoir verb: Je me égare -> Je m'égare
        words = words.replaceFirst("(.*\\s[mst])e\\s([aeéiou].*)".toRegex(), "$1'$2")

        // Sound change for jeter -> je jète.
        words = words.replaceFirst(
            "([\\w\\s]+)[eé]([cglmnprst]e($|\\s|nt|s))".toRegex(),
            "$1è$2"
        )

        // Hanging oye: J'envoye -> J'envoie
        words = words
            .replaceFirst("(oye)$".toRegex(), "oie")
            .replaceFirst("(oyes)$".toRegex(), "oies")
            .replaceFirst("(oyent)$".toRegex(), "oient")
        return words
    }


}