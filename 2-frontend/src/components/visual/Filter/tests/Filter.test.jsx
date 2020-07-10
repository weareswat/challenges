import React from 'react';
import { shallow } from 'enzyme';
import Select from "react-select";
import Filter from "../Filter";

const getShallowWrapper = ({
  options = {},
  onChangeFilter = () => {}
}) => (
  shallow(
    <Filter
      options={options}
      onChangeFilter={onChangeFilter}
    />
  )
);

const getSelect = wrapper => wrapper.find(Select).at(0);

const getInput = wrapper => wrapper.find("input").at(0);

describe("Filter", () => {
  it("should render a Select and an input", () => {
    const wrapper = getShallowWrapper({});
    expect(getSelect(wrapper).exists()).toBeTruthy();
    expect(getInput(wrapper).exists()).toBeTruthy();
  });

  it("should call onChangeFilter when first render", () => {
    const mockOnChangeFilter = jest.fn();
    getShallowWrapper({
      onChangeFilter: mockOnChangeFilter
    });
    expect(mockOnChangeFilter).toHaveBeenCalledTimes(1);
  });

  it("should call onChangeFilter when selected option changes", () => {
    const mockOnChangeFilter = jest.fn();
    const mockOption = {
      label: "mockLabel",
      value: "mockValue"
    };
    const wrapper = getShallowWrapper({
      onChangeFilter: mockOnChangeFilter
    });
    getSelect(wrapper).prop("onChange")(mockOption);
    expect(mockOnChangeFilter.mock.calls[1][0]).toEqual(mockOption.value);
  });

  it("should call onChangeFilter when input changes", () => {
    const mockOnChangeFilter = jest.fn();
    const wrapper = getShallowWrapper({
      onChangeFilter: mockOnChangeFilter
    });
    getInput(wrapper).simulate("change", {
      target: {
        value: "mockThisNewInput"
      }
    });
    expect(mockOnChangeFilter.mock.calls[1][1]).toEqual("mockThisNewInput");
  });
});