import axios from 'axios'

export default class EncryptingService {
    static SERVER_ADDRESS = 'http://localhost:8085'
    // static SERVER_ADDRESS = process.env.BACKEND_URL

    static async encrypt(text) {
        const url = this.SERVER_ADDRESS + '/api/v1/encrypt'

        const response = axios.post(
            url, {
                request: text
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        })

        return response
    }

    static async decrypt(text) {
        const url = this.SERVER_ADDRESS + '/api/v1/decrypt'

        const response = await axios.post(
            url, {
                text: text,
                key: 'SOL'
            }, 
            {
                headers: {
                    'Content-Type': 'application/json',
            }
        })
        
        return response
    }
}