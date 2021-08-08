@file:Suppress("unused")

package dev.androidbroadcast.ums.analytics.hms

import android.content.Context
import android.os.Bundle
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.analytics.type.HAEventType
import com.huawei.hms.analytics.type.HAParamType
import dev.androidbroadcast.ums.analytics.Analytics
import dev.androidbroadcast.ums.analytics.Events

public class HmsAnalytics(context: Context) : Analytics {

    private val analytics: HiAnalyticsInstance = HiAnalytics.getInstance(context)

    override fun logEvent(name: String, params: Map<String, Any>) {
        val hmsEventName = eventsMap[name] ?: name
        analytics.onEvent(
            hmsEventName,
            addMandatoryParams(hmsEventName, params).asHmsParams()
        )
    }

    override fun setUserId(userId: String?) {
        analytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String?) {
        analytics.setUserProfile(name, value)
    }

    override fun setSessionTimeoutDuration(milliseconds: Long) {
        analytics.setSessionDuration(milliseconds)
    }

    override fun setDefaultEventParameters(params: Bundle) {
        analytics.addDefaultEventParams(params)
    }

    override fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        analytics.setAnalyticsEnabled(enabled)
    }

    override fun resetAnalyticsData() {
        analytics.clearCachedData()
    }
}

private val eventsMap = mapOf(
    Events.ADD_PAYMENT_INFO to HAEventType.CREATEPAYMENTINFO,
    Events.ADD_TO_CART to HAEventType.ADDPRODUCT2CART,
    Events.ADD_TO_WISHLIST to HAEventType.ADDPRODUCT2WISHLIST,
    Events.PURCHASE to HAEventType.COMPLETEPURCHASE,

    Events.APP_OPEN to HAEventType.STARTAPP,

    Events.LEVEL_START to HAEventType.STARTLEVEL,
    Events.LEVEL_END to HAEventType.COMPLETELEVEL,
    Events.LEVEL_UP to HAEventType.UPGRADELEVEL,
    Events.UNLOCK_ACHIEVEMENT to HAEventType.OBTAINACHIEVEMENT,
    Events.POST_SCORE to HAEventType.SUBMITSCORE,

    Events.EARN_VIRTUAL_CURRENCY to HAEventType.WINVIRTUALCOIN,
    Events.SPEND_VIRTUAL_CURRENCY to HAEventType.CONSUMEVIRTUALCOIN,

    Events.LOGIN to HAEventType.SIGNIN,
    Events.SIGN_UP to HAEventType.REGISTERACCOUNT,
    Events.SHARE to HAEventType.SHARECONTENT,
    Events.JOIN_GROUP to HAEventType.JOINUSERGROUP,

    Events.BEGIN_CHECKOUT to HAEventType.STARTCHECKOUT,
    Events.CAMPAIGN_DETAILS to HAEventType.VIEWCAMPAIGN,
    Events.GENERATE_LEAD to HAEventType.OBTAINLEADS,
    Events.REMOVE_FROM_CART to HAEventType.DELPRODUCTFROMCART,

    Events.TUTORIAL_BEGIN to HAEventType.STARTTUTORIAL,
    Events.TUTORIAL_COMPLETE to HAEventType.COMPLETETUTORIAL,

    Events.VIEW_ITEM to HAEventType.VIEWPRODUCT,
    Events.VIEW_ITEM_LIST to HAEventType.VIEWPRODUCTLIST,
    Events.VIEW_SEARCH_RESULTS to HAEventType.VIEWSEARCHRESULT,

    Events.SEARCH to HAEventType.SEARCH
)

private val paramsMap = mapOf(
    /** CREATEPAYMENTINFO Section**/
    Events.Param.PAYMENT_TYPE to HAParamType.PAYTYPE,

    /** ADDPRODUCT2CART, ADDPRODUCT2WISHLIST **/
    Events.Param.CURRENCY to HAParamType.CURRNAME,
    Events.Param.VALUE to HAParamType.PRICE,
    Events.Param.ITEM_NAME to HAParamType.PRODUCTNAME,

    /* STARTLEVEL, COMPLETELEVEL Section**/
    Events.Param.LEVEL_NAME to HAParamType.LEVELNAME,

    /* COMPLETELEVEL Section**/
    Events.Param.SUCCESS to HAParamType.RESULT,

    /* UPGRADELEVEL Section**/
    Events.Param.LEVEL to HAParamType.LEVELID,
    Events.Param.CHARACTER to HAParamType.ROLENAME,

    /* OBTAINACHIEVEMENT Section**/
    Events.Param.ACHIEVEMENT_ID to HAParamType.ACHIEVEMENTID,
    Events.Param.ACHIEVEMENT_ID to HAParamType.ACHIEVEMENTID,

    /* SUBMITSCORE Section**/
    Events.Param.SCORE to HAParamType.SCORE,

    /* WINVIRTUALCOIN, CONSUMEVIRTUALCOIN Section**/
    Events.Param.VIRTUAL_CURRENCY_NAME to HAParamType.VIRTUALCURRNAME,

    /* SHARE Section**/
    Events.Param.CONTENT_TYPE to HAParamType.CONTENTTYPE,
    Events.Param.ITEM_ID to HAParamType.PRODUCTID,

    /* JOINUSERGROUP Section**/
    Events.Param.GROUP_ID to HAParamType.USERGROUPID,

    /* VIEWCAMPAIGN Section**/
    Events.Param.MEDIUM to HAParamType.MEDIUM,
    Events.Param.SOURCE to HAParamType.SOURCE,
    Events.Param.CAMPAIGN to HAParamType.PROMOTIONNAME,
    Events.Param.CONTENT to HAParamType.CONTENT,
    Events.Param.TERM to HAParamType.KEYWORDS,
    Events.Param.ACLID to HAParamType.CLICKID,
    Events.Param.CP1 to HAParamType.EXTENDPARAM,

    /* VIEWSEARCHRESULT Section**/
    Events.Param.SEARCH_TERM to HAParamType.SEARCHKEYWORDS,

    /* COMMON Section**/
    Events.Param.PRICE to HAParamType.PRICE,
    Events.Param.QUANTITY to HAParamType.QUANTITY
)

private fun toHmsParameter(hmsEventName: String, paramName: String): String {
    when (paramName) {
        Events.Param.VALUE -> when (hmsEventName) {
            HAEventType.ADDPRODUCT2CART, HAEventType.ADDPRODUCT2WISHLIST -> return HAParamType.PRICE
            HAEventType.WINVIRTUALCOIN, HAEventType.CONSUMEVIRTUALCOIN, HAEventType.COMPLETEPURCHASE ->
                return HAParamType.REVENUE
        }
        Events.Param.METHOD -> when (hmsEventName) {
            HAEventType.SIGNIN -> return HAParamType.CHANNEL
            HAEventType.REGISTERACCOUNT -> return HAParamType.REGISTMETHOD
        }
    }
    return paramsMap[paramName] ?: paramName
}


private const val EMPTY_PARAMETER: String = "-"

private fun addMandatoryParams(hmsEventName: String, params: Map<String, Any>): Map<String, Any> {
    var modifiedParams = params
    when (hmsEventName) {
        HAEventType.SIGNIN, HAEventType.REGISTERACCOUNT -> {
            if (HAParamType.OCCURREDTIME !in params) {
                modifiedParams = params + (HAParamType.OCCURREDTIME to System.currentTimeMillis())
            }
        }
        HAEventType.ADDPRODUCT2CART, HAEventType.ADDPRODUCT2WISHLIST -> {
            val additionalParams = mutableMapOf<String, Any>()
            if (HAParamType.PRODUCTID !in params) {
                additionalParams[HAParamType.PRODUCTID] = EMPTY_PARAMETER
            }
            if (HAParamType.PRODUCTNAME !in params) {
                additionalParams[HAParamType.PRODUCTNAME] = EMPTY_PARAMETER
            }

            if (additionalParams.isNotEmpty()) {
                modifiedParams = modifiedParams + additionalParams
            }
        }
        HAEventType.VIEWPRODUCTLIST -> {
            if (HAParamType.CATEGORY !in params) {
                modifiedParams = params + (HAParamType.CATEGORY to EMPTY_PARAMETER)
            }
        }
    }
    return modifiedParams
}

private fun Map<String, Any>.asHmsParams(): Bundle {
    if (isEmpty()) return Bundle.EMPTY

    val bundle = Bundle(size)
    forEach { (key, value) ->
        when (value) {
            is Byte -> bundle.putByte(key, value)
            is Char -> bundle.putChar(key, value)
            is Short -> bundle.putShort(key, value)
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)

            is Float -> bundle.putFloat(key, value)
            is Double -> bundle.putDouble(key, value)

            is String -> bundle.putString(key, value)

            is Boolean -> bundle.putBoolean(key, value)

            is ByteArray -> bundle.putByteArray(key, value)
            is CharArray -> bundle.putCharArray(key, value)
            is ShortArray -> bundle.putShortArray(key, value)
            is IntArray -> bundle.putIntArray(key, value)
            is LongArray -> bundle.putLongArray(key, value)
            is FloatArray -> bundle.putFloatArray(key, value)
            is DoubleArray -> bundle.putDoubleArray(key, value)
            is BooleanArray -> bundle.putBooleanArray(key, value)
        }
    }
    return bundle
}