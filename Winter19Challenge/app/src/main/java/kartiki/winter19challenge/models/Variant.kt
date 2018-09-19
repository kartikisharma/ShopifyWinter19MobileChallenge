package kartiki.winter19challenge.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable


/**
 * Created by Kartiki on 2018-09-18.
 */
class Variant(`in`: Parcel) : Parcelable {
    @SerializedName("inventory_quantity")
    val inventoryQuantity: String = `in`.readString()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(inventoryQuantity)
    }

    companion object CREATOR : Parcelable.Creator<Variant> {
        override fun createFromParcel(`in`: Parcel): Variant {
            return Variant(`in`)
        }

        override fun newArray(size: Int): Array<Variant?> {
            return arrayOfNulls(size)
        }

    }
}