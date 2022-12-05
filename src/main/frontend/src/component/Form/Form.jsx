import React from 'react'
import { TextArea } from '../UI/TextArea/TextArea'
import Button from 'react-bootstrap/Button'
import { useState } from 'react'
import EncryptingService from '../../API/EncryptingService'

export const Form = () => {
    const [text, setText] = useState('')
    const [serverText, setServerText] = useState('')

    function clearText() {
        setText('')
        setServerText('')
    }

    function encrypt() {
        EncryptingService.encrypt(text)
            .then(res => setServerText(res.data.symbols.join()))
            .catch(e => console.log(e))
    }

    function decrypt() {
        EncryptingService.decrypt(text)
            .then(res => setServerText(res.data.text))
            .catch(e => console.log(e))
    }

    function saveToClipboard() {
        navigator.clipboard.writeText(serverText)
    }

    return (
        <div>
            <TextArea value={text} onChange={e => setText(e.target.value)}/>
            <div className="mt-2">
                <Button variant="primary" className="me-2" onClick={encrypt}>Зашифровать</Button>
                <Button variant="success" className="me-2" onClick={decrypt}>Расшифровать</Button>
                <Button variant="danger" onClick={clearText}>Очистить</Button>
            </div>
            <div className="mt-2">
                <TextArea value={serverText} onChange={e => setServerText(e.target.value)} disabled="disabled"/>
            </div>
            <div className="mt-2 block-right">
                <Button variant="info" classname="me-2" onClick={saveToClipboard}>
                    <span className="white">Скопировать в буфер обмена </span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16" color="white">
                        <path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
                    </svg>
                </Button>
            </div>
        </div>
    )
}
