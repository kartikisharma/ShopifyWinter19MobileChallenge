package kartiki.winter19challenge.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable


/**
 * Created by Kartiki on 2018-09-17.
 */
class ProductImage : Parcelable {

    @SerializedName("src")
    var imageUrl: String? = null
        private set

    constructor(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(`in`: Parcel) {
        this.imageUrl = `in`.readString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.imageUrl)
    }

    override fun toString(): String {
        return "ProductImage{" +
                "src='" + imageUrl + '\'' +
                '}'
    }

    companion object CREATOR : Parcelable.Creator<ProductImage> {
        override fun createFromParcel(`in`: Parcel): ProductImage {
            return ProductImage(`in`)
        }

        override fun newArray(size: Int): Array<ProductImage?> {
            return arrayOfNulls(size)
        }

    }
}