package kartiki.winter19challenge

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable


/**
 * Created by Kartiki on 2018-09-17.
 */
class Product(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    val id: String

    @SerializedName("title")
    val title: String

    @SerializedName("body_html")
    val description: String

    @SerializedName("image")
    val productImage: ProductImage

    @SerializedName("vendor")
    val vendor: String

    @SerializedName("product_type")
    val productType: String

    @SerializedName("created_at")
    val dateCreated: String

    // do not see purpose on UI
    @SerializedName("handle")
    val handle: String

    @SerializedName("updated_at")
    val dateUpdated: String

    @SerializedName("published_at")
    val datePublished: String

    @SerializedName("tags")
    val tags: String

    fun getTagList(): List<String> {
        return tags.split(", ")
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
        parcel.writeString(this.productType)
        parcel.writeString(this.dateCreated)
        parcel.writeString(this.handle)
        parcel.writeString(this.dateUpdated)
        parcel.writeString(this.datePublished)
        parcel.writeString(this.tags)
//        parcel.writeList(this.variants)
    }

    init {
        this.id = `in`.readString()
        this.title = `in`.readString()
        this.description = `in`.readString()
        this.productImage = `in`.readParcelable<ProductImage>(ProductImage::class.java.classLoader)
        this.vendor = `in`.readString()
        this.productType = `in`.readString()
        this.dateCreated = `in`.readString()
        this.handle = `in`.readString()
        this.dateUpdated = `in`.readString()
        this.datePublished = `in`.readString()
        this.tags = `in`.readString()
//        variants = ArrayList()
//        `in`.readList(variants, Variant::class.java!!.getClassLoader())
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