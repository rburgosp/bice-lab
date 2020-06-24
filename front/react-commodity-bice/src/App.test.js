import React from 'react';
import { render } from '@testing-library/react';
import App from './component/App';
import { shallow } from 'enzyme';
test('Componente App Iniciado Correctamente', () => {
  const { getByText } = render(<App />);
  const linkElement = getByText("Crear");
  expect(linkElement).toBeInTheDocument();
});



