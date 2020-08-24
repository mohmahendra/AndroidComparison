import { createStackNavigator } from 'react-navigation-stack'
import { createAppContainer } from 'react-navigation'
import Home from '../screen/home'
import Photo from '../screen/photo'

const screens = {
    Home: {
        screen: Home
    },
    Photo: {
        screen: Photo
    },
}

const HomeStack = createStackNavigator(screens);

export default createAppContainer(HomeStack);