import axios from 'axios'

export default class EncryptingService {
    static SERVER_ADDRESS = 'http://localhost:8080'

    static async encrypt(text) {
        let url = this.SERVER_ADDRESS + '/api/v1/encrypt'

        const response = axios.post(
            url, {
                text: 'Hello'
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        }).then((res) => {
            console.log(res)
        }).catch(e => console.log(e))
        
        
        return response
    }

    static async decrypt(text) {
        let url = this.SERVER_ADDRESS + '/api/v1/decrypt'
        let test = [
            {
                text: 'A'
            },
            {
                text: 'A'
            },
            {
                text: 'A'
            },
            {
                text: 'A'
            },
            {
                text: 'A'
            },
            {
                text: 'A'
            }
        ]

        const response = axios.post(
            url, {
                text: test
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        }).then((res) => {
            console.log(res)
        }).catch(e => console.log(e))
        
        
        return response
    }
}