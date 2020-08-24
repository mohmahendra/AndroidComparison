import React, { useState, Component } from 'react';
import { FlatList, StyleSheet, TouchableOpacity, Text, View} from 'react-native';
import { Avatar } from 'react-native-elements';
import Icon from 'react-native-vector-icons/FontAwesome'

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      loading: false,
      // Add key for data
      data: [
        { name: 'Zazan Surazan Markonan', username: 'Z4Z4N', message: "Hatiku dagdigdug melulu, cause guess i'm just a playdate to you. Laaaaa Laaaaa Laaa Laaaa Laaaa",},
        { name: 'RPBA', username: 'RP84', message:'Aku Monster kependekan?' ,},
        { name: 'Dragonewt', username: 'Naga', message:'Ular naga panjangnya',},
        { name: 'Mustofa', username: 'Mustoph4', message: 'Aku tergila-gila sama seorang bidadari',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
        { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      ],
      page: 1,
      error:null,
      refreshing:false
    };
  }

  presshandler = () => {
    this.props.navigation.navigate('Photo');
    // navigation.navigate('Photo');
  }

  handleLoadMore = () => {
    var keyThreshold = 11
    var newData = [
      { name: 'RPBA', username: 'RP84', message:'Aku Monster kependekan?'},
      { name: 'Dragonewt', username: 'Naga', message:'Ular naga panjangnya',},
      { name: 'Zazan Surazan Markonan', username: 'Z4Z4N', message: "Hatiku dagdigdug melulu, cause guess i'm just a playdate to you. Laaaaa Laaaaa Laaa Laaaa Laaaa",},
      { name: 'Mustofa', username: 'Mustoph4', message: 'Aku tergila-gila sama seorang bidadari',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
      { name: 'Zazan', username: 'Z4Z4N', message: 'Hatiku dagdigdug melulu',},
    ]
    let data = this.state.data.concat(newData)
    this.setState({
      data: data
    })
  }

  render() {
    return(
      <View style={{ justifyContent: "flex-start" }}>
      <FlatList
          data = {this.state.data}
          renderItem={({ item }) => (
            <View style={styles.tweet}>
              <View style={{ flex: 1, flexDirection: 'row'}} >
                <Avatar
                  size="medium"
                  rounded
                  title=""
                  backgroundColor="red"
                />
                <View
                  style={{
                    flexDirection: "column",
                    justifyContent: "flex-start",
                  }}
                >
                  <View
                    style={{
                      flexDirection: "row",
                      justifyContent: "flex-start",
                    }}
                  >
                    <Text
                      style={{
                        paddingLeft: 15,
                        fontWeight: "bold",
                        fontSize: 14,
                      }}
                    >
                      {item.name}
                    </Text>
                    <Text
                      style={{
                        paddingLeft: 10,
                        color: "#aaa",
                        fontSize: 14,
                      }}
                    >
                      {"@" + item.username}
                    </Text>
                  </View>
                  <Text style={styles.tweetText}>{item.message}</Text>
                </View>
              </View>
              <View style={styles.tweetFooter}>
                <View style={styles.footerIcons}>
                  <Icon name="comments" />
                  <Text style={styles.badgeCount}>5</Text>
                </View>
                <View style={styles.footerIcons}>
                  <Icon name="retweet" /> 
                  <Text style={styles.badgeCount}>10</Text>
                </View>
                <View style={styles.footerIcons}>
                  <Icon name="heart-o" />
                  <Text style={styles.badgeCount}>15</Text>
                </View>
                <View style={styles.footerIcons}>
                  <Icon name="share-alt" />
                  <Text style={styles.badgeCount}>20</Text>
                </View>
              </View>
            </View>
          )}
          onEndReached={this.handleLoadMore}
          onEndThreshold={10}
        />
        <View style={styles.container}>
          <TouchableOpacity style={styles.fab} onPress={this.presshandler}>
            <Icon
              name="camera" 
              color="#000000"
              size={20}
            />
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
      flex : 1,
      paddingTop: 20
  },
  item: {
      padding : 10,
      fontSize : 18,
      height : 44,
  },
  tweet: {
    paddingTop: 10,
    paddingBottom: 10,
    paddingLeft: 10,
    paddingRight: 10,
    borderBottomColor: "#bbb",
    borderBottomWidth: StyleSheet.hairlineWidth,
    flexDirection: "column"
  },
  tweetName:{},
  tweetUsername:{

  },
  footerIcons: {
    flexDirection: "row",
    alignItems: "center"
  },
  badgeCount: {
    fontSize: 12,
    paddingLeft: 5
  },
  tweetText: {
    fontSize: 14,
    paddingLeft: 15,
    paddingBottom: 5,
  },
  tweetFooter: {
    flexDirection: "row",
    justifyContent: "space-around",
    paddingLeft: 35
  },
  fab:{
    height: 60,
    width: 60,
    borderRadius: 200,
    position: 'absolute',
    bottom: 50,
    right: 20,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor:'#32cd32',
  },
});