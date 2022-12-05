import axios from 'axios'

export default class EncryptingService {
    static SERVER_ADDRESS = 'http://localhost:8080'

    static async encrypt(text) {
        let url = this.SERVER_ADDRESS + '/api/v1/encrypt'

        const response = axios.post(
            url, {
                request: text
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        }).then((res) => {
            return res
        }).catch(e => console.log(e))
        
        
        return response
    }

    static async decrypt(text) {
        let url = this.SERVER_ADDRESS + '/api/v1/decrypt'

        const response = axios.post(
            url, {
                text: text,
                key: 'SOL'
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        }).catch(e => console.log(e))
        
        
        return response
    }
}