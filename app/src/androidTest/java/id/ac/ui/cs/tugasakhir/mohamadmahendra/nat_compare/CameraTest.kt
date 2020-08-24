package id.ac.ui.cs.tugasakhir.mohamadmahendra.nat_compare

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CameraTest {

    @get:Rule
    var activityRule : IntentsTestRule<CameraActivity> = IntentsTestRule(CameraActivity::class.java)

    @Before
    fun stubCameraIntent() {
        val result : Instrumentation.ActivityResult = createImageCaptureActivityResultStub(activityRule.activity)
        Intents.intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result)
    }

    @Test
    fun test_takeMockPhotos() {
        Espresso.onView(ViewMatchers.withId(R.id.imageView))
            .check(ViewAssertions.matches(hasDrawable()))
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.imageView))
            .check(ViewAssertions.matches(hasDrawable()))
    }

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
}