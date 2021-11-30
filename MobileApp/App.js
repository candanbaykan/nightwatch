/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

 import React, { useEffect, useState } from 'react';
 import { ActivityIndicator, FlatList, Text, View } from 'react-native';
 
 export default App = () => {
   const [isLoading, setLoading] = useState(true);
   const [data, setData] = useState([]);
   const baseUrl = Platform.OS === 'android' ? 'http://10.0.2.2:8080' : 'http://localhost:8080';
 
   const getUsername = async () => {
      try {
       const response = await fetch(baseUrl + '/api/v1/users');
       const json = await response.json();
       setData(json);
     } catch (error) {
       console.error(error);
     } finally {
       setLoading(false);
     }
   }
   const postUser = async () => {
    return fetch(baseUrl + '/api/v1/users')
    .then((response) => response.json())
    .then((json) => {
      return json;
    })
    .catch((error) => {
      console.error(error);
    });
   }
   fetch(baseUrl + '/api/v1/users', {
  method: 'POST',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    "username": "Hamid",
    "password": "1234",
    "roleId": 1
  })
});
  
  const deleteUser = async () => {
    return fetch(baseUrl + '/api/v1/users')
    .then((response) => response.json())
    .then((json) => {
      return json;
    })
    .catch((error) => {
      console.error(error);
    });
   }
   fetch(baseUrl + '/api/v1/users', {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "id":10
    })
  })
   
 
   useEffect(() => {
     getUsername();
     deleteUser();
   }, []);
 
   return (
     <View style={{ flex: 1, padding: 24 }}>
       {isLoading ? <ActivityIndicator/> : (
         <FlatList
           data={data}
           keyExtractor={({ id }, index) => id}
           renderItem={({ item }) => (
             <Text>{item.id} - {item.username}</Text>
           )}
         />
       )}
     </View>
   );
 };