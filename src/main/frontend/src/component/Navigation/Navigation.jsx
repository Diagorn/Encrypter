import React, { useState } from 'react';
import { Container, Nav, Navbar, NavbarBrand } from 'react-bootstrap';
import { Modal } from 'react-bootstrap';
import { Button } from 'react-bootstrap';

import classes from './Navigation.module.css'

export const Navigation = () => {

    const [modalShown, setModalShown] = useState(false)
    const [modalTitle, setModalTitle] = useState('')
    const [modalText, setModalText] = useState('')

    const showAboutDeveloperModal = () => {
        setModalTitle('О разработчике')
        setModalText('Разработчик - Гасин Михаил. GitHub: <a href="https://github.com/Diagorn" target="_blank">Diagorn</a>')
        setModalShown(true)
    }
    const showAboutIdeaModal = () => {
        setModalTitle('О идее приложения')
        setModalText('Идея родилась из онлайн-квеста <a href="https://vk.com/pb_inei_mpei" target="_blank">ПБ ИнЭИ</a>, где я придумал собственный шифр, основанный на нотах.\n' + 
        'Участникам квеста предлагалось угадать принцип шифрования по зашифрованному тексту')
        setModalShown(true)
    }
    const hideModal = () => setModalShown(false)

    return (
        <Navbar variant="light" expand="lg" className={classes.NavbarBg}>
            <Container>
                <NavbarBrand>Encrypter</NavbarBrand>
                <Nav>
                    <Nav.Link onClick={showAboutDeveloperModal}>О разработчике</Nav.Link>
                    <Nav.Link onClick={showAboutIdeaModal}>О идее приложения</Nav.Link>
                </Nav>
            </Container>

            <Modal centered size="lg" animation={true} show={modalShown} onHide={hideModal}>
                <Modal.Header closeButton>
                    <Modal.Title>{modalTitle}</Modal.Title>
                </Modal.Header>
                <Modal.Body><span dangerouslySetInnerHTML={{ __html: modalText }}></span></Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={hideModal}>Закрыть</Button>
                </Modal.Footer>
            </Modal>
        </Navbar>
    )
}