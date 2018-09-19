package kartiki.winter19challenge.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable


/**
 * Created by Kartiki on 2018-09-17.
 */
class Product(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    val id: String = `in`.readString()

    @SerializedName("title")
    val title: String = `in`.readString()

    @SerializedName("body_html")
    val description: String = `in`.readString()

    @SerializedName("image")
    val productImage: ProductImage = `in`.readParcelable(ProductImage::class.java.classLoader)

    @SerializedName("vendor")
    val vendor: String = `in`.readString()

//    @SerializedName("product_type")
//    val productType: String = `in`.readString()

//    @SerializedName("created_at")
//    val dateCreated: String = `in`.readString()

//    @SerializedName("handle")
//    val handle: String = `in`.readString()

//    @SerializedName("updated_at")
//    val dateUpdated: String = `in`.readString()

//    @SerializedName("published_at")
//    val datePublished: String = `in`.readString()

    @SerializedName("tags")
    val tags: String = `in`.readString()

    @SerializedName("variants")
    val variants: List<Variant> = ArrayList()

    init {
        `in`.readList(variants, Variant::class.java.classLoader)
    }

    private var hasCalculatedQuantity = false
    private var combinedQuantity: Int = 0

    fun getTagList(): List<String> {
        return tags.split(", ")
    }

    fun getQuantity(): Int {
        if (!hasCalculatedQuantity) {
            for (item in variants) {
                combinedQuantity = combinedQuantity.plus(item.inventoryQuantity.toInt())
            }
            hasCalculatedQuantity = true
        }
        return combinedQuantity
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.id)
        parcel.writeString(this.title)
        parcel.writeString(this.description)
        parcel.writeParcelable(this.productImage, 0)
        parcel.writeString(this.vendor)
//        parcel.writeString(this.productType)
//        parcel.writeString(this.dateCreated)
//        parcel.writeString(this.handle)
//        parcel.writeString(this.dateUpdated)
//        parcel.writeString(this.datePublished)
        parcel.writeString(this.tags)
        parcel.writeList(this.variants)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(`in`: Parcel): Product {
            return Product(`in`)
        }

        override fun newArray(p0: Int): Array<Product?> {
            return arrayOfNulls(p0)
        }
    }
}