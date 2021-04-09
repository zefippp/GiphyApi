package com.infinity.giphy.model.search

import java.io.Serializable

class GifItem @JvmOverloads constructor(
    var gif: DownsizedMedium = DownsizedMedium(
        "350",
        "1",
        "https://media1.tenor.com/images/f7e96ebc01482872f56b9bf0ed3ffb51/tenor.gif?itemid=11964231",
        "350"
    )
) : Serializable