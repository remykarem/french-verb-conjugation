import org.junit.Test
import kotlin.test.assertEquals

class FrenchVerbTest {


    @Test
    fun `present -ir dormir`() {
        val verb = FrenchVerb("dormir")
        assertEquals(listOf("dors", "dors", "dort", "dormons", "dormez", "dorment"), verb.present)
    }

    @Test
    fun `past -ir dormir`() {
        val verb = FrenchVerb("dormir")
        assertEquals(listOf("dormi", "dormi", "dormi", "dormi", "dormi", "dormi"), verb.past)
    }

    @Test
    fun `imperative -ir dormir`() {
        val verb = FrenchVerb("dormir")
        assertEquals(listOf("dors", "dormez"), verb.imperative)
    }
    @Test
    fun `present -tir mentir`() {
        val verb = FrenchVerb("mentir")
        assertEquals(listOf("mens", "mens", "ment", "mentons", "mentez", "mentent"), verb.present)
    }

    @Test
    fun `past -tir mentir`() {
        val verb = FrenchVerb("mentir")
        assertEquals(listOf("menti", "menti", "menti", "menti", "menti", "menti"), verb.past)
    }

    @Test
    fun `imperative -tir mentir`() {
        val verb = FrenchVerb("mentir")
        assertEquals(listOf("mens", "mentez"), verb.imperative)
    }

    @Test
    fun `present -dre apprendre`() {
        val verb = FrenchVerb("apprendre")
        assertEquals(listOf("apprends", "apprends", "apprend", "apprenons", "apprenez", "apprennent"), verb.present)
    }

    @Test
    fun `past -dre apprendre`() {
        val verb = FrenchVerb("apprendre")
        assertEquals(listOf("appris", "appris", "appris", "appris", "appris", "appris"), verb.past)
    }

    @Test
    fun `imperative -dre apprendre`() {
        val verb = FrenchVerb("apprendre")
        assertEquals(listOf("apprends", "apprenez"), verb.imperative)
    }

    @Test
    fun `present -er manger`() {
        val verb = FrenchVerb("manger")
        assertEquals(listOf("mange", "manges", "mange", "mangeons", "mangez", "mangent"), verb.present)
    }

    @Test
    fun `past -er manger`() {
        val verb = FrenchVerb("manger")
        assertEquals(listOf("mangé", "mangé", "mangé", "mangé", "mangé", "mangé"), verb.past)
    }

    @Test
    fun `imperative -er manger`() {
        val verb = FrenchVerb("manger")
        assertEquals(listOf("mange", "mangez"), verb.imperative)
    }

    @Test
    fun `present -er jeter`() {
        val verb = FrenchVerb("jeter")
        assertEquals(listOf("jette", "jettes", "jette", "jetons", "jetez", "jettent"), verb.present)
    }

    @Test
    fun `past -er jeter`() {
        val verb = FrenchVerb("jeter")
        assertEquals(listOf("jeté", "jeté", "jeté", "jeté", "jeté", "jeté"), verb.past)
    }

    @Test
    fun `imperative -er jeter`() {
        val verb = FrenchVerb("jeter")
        assertEquals(listOf("jette", "jetez"), verb.imperative)
    }

    @Test
    fun `present -er placer`() {
        val verb = FrenchVerb("placer")
        assertEquals(listOf("place", "places", "place", "plaçons", "placez", "placent"), verb.present)
    }

    @Test
    fun `past -er placer`() {
        val verb = FrenchVerb("placer")
        assertEquals(listOf("placé", "placé", "placé", "placé", "placé", "placé"), verb.past)
    }

    @Test
    fun `imperative -er placer`() {
        val verb = FrenchVerb("placer")
        assertEquals(listOf("place", "placez"), verb.imperative)
    }

    @Test
    fun `present -er se promener`() {
        val verb = FrenchVerb("se promener")
        assertEquals(listOf("me promène", "te promènes", "se promène", "nous promenons", "vous promenez", "se promènent"), verb.present)
    }

    @Test
    fun `past -er se promener`() {
        val verb = FrenchVerb("se promener")
        assertEquals(listOf("me suis promené", "te es promené", "se est promené", "nous sommes promené",
            "vous êtes promené", "se sont promené"), verb.past)
    }

    @Test
    fun `imperative -er se promener`() {
        val verb = FrenchVerb("se promener")
        assertEquals(listOf("promène-toi", "promenez-vous"), verb.imperative)
    }

    @Test
    fun `present -dre descendre`() {
        val verb = FrenchVerb("descendre")
        assertEquals(listOf("descends", "descends", "descend", "descendons", "descendez", "descendent"), verb.present)
    }

    @Test
    fun `past -dre descendre`() {
        val verb = FrenchVerb("descendre")
        assertEquals(
            listOf(
                "suis descendu(e)",
                "es descendu(e)",
                "est descendu(e)",
                "sommes descendu(e)s",
                "êtes descendu(e)s",
                "sont descendu(e)s"
            ),
            verb.past
        )
    }

    @Test
    fun `imperative -dre descendre`() {
        val verb = FrenchVerb("descendre")
        assertEquals(listOf("descends", "descendez"), verb.imperative)
    }

}