package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare


import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PerformanceTest  {

    @get:Rule
    var activityRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_isRecyclerViewVisible_onLaunch() {
        Thread.sleep(5000)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_scrollEndlessButSwiping_rv() {
        val swipeSlow : GeneralSwipeAction = GeneralSwipeAction(Swipe.SLOW, GeneralLocation.CENTER,
            GeneralLocation.TOP_CENTER, Press.FINGER)
        kotlin.repeat(30) {
            onView(withId(R.id.recyclerView)).perform(swipeSlow)
        }
    }

//    @Test
//    fun test_takePhotosThenDisplay() {
//        onView(withId(R.id.fab)).perform(click())
//        onView(withId(R.id.imageView)).perform(click())
//        Thread.sleep(15000)
//        onView(withId(R.id.imageView)).check(matches(hasDrawable()))
//    }


    fun hasDrawable() : BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has drawable")
            }
            override fun matchesSafely(imageView: ImageView) : Boolean {
                return imageView.drawable != null
            }
        }
    }

    fun createImageCaptureActivityResultStub(activity : Activity) : Instrumentation.ActivityResult {
        val bundle = Bundle()
        bundle.putParcelable("IMG_DATA", BitmapFactory.decodeResource(activity.resources, R.mipmap.ic_launcher))
        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)
        // Create the ActivityResult with the Intent.
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }

    class ScrollToBottomAction : ViewAction {
        override fun getDescription(): String {
            return "scroll RecyclerView to bottom"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount
            val position = itemCount?.minus(1) ?: 0
            recyclerView.scrollToPosition(position)
            uiController?.loopMainThreadUntilIdle()
        }
    }

}