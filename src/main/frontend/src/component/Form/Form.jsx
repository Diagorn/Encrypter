import React from 'react'
import { TextArea } from '../UI/TextArea/TextArea'
import Button from 'react-bootstrap/Button'
import { useState } from 'react'
import EncryptingService from '../API/EncryptingService'

export const Form = () => {
    const [text, setText] = useState('')

    function clearText() {
        setText('')
    }

    function encrypt() {
        EncryptingService.encrypt('Hello world!');
    }

    function decrypt() {
        EncryptingService.decrypt('Hello world!');
    }

    return (
        <div>
            <TextArea value={text} onChange={e => setText(e.target.value)}/>
            <div className='mt-2'>
                <Button variant="primary" className="me-2" onClick={encrypt}>Зашифровать</Button>
                <Button variant="success" className="me-2" onClick={decrypt}>Расшифровать</Button>
                <Button variant="danger" onClick={clearText}>Очистить</Button>
            </div>
        </div>
    )
}
