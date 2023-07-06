package rs.raf.projekat1.rmanutritiont.data.model

data class Meal(
    val id: Int? = 0,
    val name: String = "",

//    val imageVector: ImageVector?,   //  Delete this later, swap with thumbnailUrl
    val thumbnailUrl: String = "",

    val ingredients: List<String>? = emptyList(),
    val cookInstructions: String = "Lorem ipsum text cook this, cook that. Lorem ipsum text cook this, cook that. ",
    val categories: List<String> = emptyList(),
    val area: String? = "Area test",
    val tagList: List<String> = emptyList(),
)
