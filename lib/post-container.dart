import 'dart:math';

import 'package:flutter/material.dart';

import 'post.dart';

class PostContainer extends StatelessWidget {
  final Post post;

  const PostContainer({Key key, this.post}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        border: Border(bottom: BorderSide(color: Colors.grey))),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                CircleAvatar(
//                  backgroundImage: NetworkImage(post.avatar),
                  backgroundColor: Colors.amber,
                ),
                Padding(
                  padding: EdgeInsets.symmetric(vertical: 14.0, horizontal: 8.0)
                ),
                Flexible(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.only(bottom: 4.0),
                        child: Row(
                          children: <Widget>[
                            Padding(
                              padding: const EdgeInsets.only(right: 4.0),
                              child: Text(
                                post.name,
                                key: Key('name_${post.name}'),
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 14.0
                                ),
                              ),
                            ),
                            Text(
                              post.username,
                              style: TextStyle(
                                color: Colors.grey[600],
                                fontSize: 12.0
                              ),
                            ),
                            Padding(
                              padding: EdgeInsets.symmetric(horizontal: 5.0),
                              child: Text('·'),
                            ),
                            Text(
                              Random().nextInt(10).toString() + 'm',
//                            post.date,
                              style: TextStyle(
                                fontSize: 12.0
                              ),
                            )
                          ],
                        ),
                      ),
                      Text(post.message),
                      Padding(
                        padding: EdgeInsets.symmetric(vertical: 5.0),
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: <Widget>[
                          Container(
                            child: Row(
                              children: <Widget>[
                                Icon(
                                  Icons.message,
                                  color: Colors.grey[500],
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(horizontal: 5.0),
                                ),
                                Text(Random().nextInt(50).toString())
                              ],
                            ),
                          ),
                          Container(
                            child: Row(
                              children: <Widget>[
                                Icon(
                                  Icons.cached,
                                  color: Colors.grey[500],
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(horizontal: 5.0),
                                ),
                                Text(Random().nextInt(50).toString())
                              ],
                            ),
                          ),
                          Container(
                            child: Row(
                              children: <Widget>[
                                Icon(
                                  Icons.favorite_border,
                                  color: Colors.grey[500],
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(horizontal: 5.0),
                                ),
                                Text(Random().nextInt(50).toString())
                              ],
                            ),
                          ),
                          Container(
                            child: Row(
                              children: <Widget>[
                                Icon(
                                  Icons.share,
                                  color: Colors.grey[500],
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(horizontal: 5.0)
                                ),
                              ],
                            ),
                          )
                        ],
                      )
                    ],
                  ),
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}