import React from 'react';
import { render } from '@testing-library/react';
import Commodity from './component/Commodity';
import { shallow } from 'enzyme';
test('Componente Commodity Iniciado Correctamente', () => {
  const { getByText } = render(<Commodity />);
  const linkElement = getByText("Total a Pagar en CLP");
  expect(linkElement).toBeInTheDocument();
});



