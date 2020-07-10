import React from 'react';
import { shallow } from 'enzyme';
import Pagination from '../Pagination';
import * as utils from '../paginationUtils';

/**
 * MOCKS **************************************************
 */
jest.mock("../paginationUtils");

const mockCanGoBack = can => {
  utils.canGoBack = () => can;
};

const mockCanGoForward = can => {
  utils.canGoForward = () => can;
};
/**
 * *********************************************************
 */

const getShallowWrapper = ({ 
  totalItems = 1,
  page = 1,
  onPageChange = () => {}
}) => {
  return shallow(
    <Pagination 
      totalItems={totalItems}
      page={page}
      onPageChange={onPageChange}
    />
  );
};

const getPrevButton = wrapper => (
  wrapper.find("#prevButton").at(0)
);

const getNextButton = wrapper => (
  wrapper.find("#nextButton").at(0)
);

const getCurrentPageElement = wrapper => (
  wrapper.find("#totalItems").at(0)
);

describe("Pagination", () => {

  describe("when can go back and can go forward", () => {
    beforeAll(() => {
      mockCanGoBack(true);
      mockCanGoForward(true);
    });

    it("should render two buttons and a span if total items is bigger than 0", () => {
      const wrapper = getShallowWrapper({
        totalItems: 1
      });
      expect(getPrevButton(wrapper).exists()).toBeTruthy();
      expect(getNextButton(wrapper).exists()).toBeTruthy();
      expect(getCurrentPageElement(wrapper).exists()).toBeTruthy();
    });

    it("should not render the number of items if totalItems is smaller than 0", () => {
      const wrapper1 = getShallowWrapper({
        totalItems: 0
      });
      expect(getCurrentPageElement(wrapper1).exists()).toBeFalsy();

      const wrapper2 = getShallowWrapper({
        totalItems: null
      });
      expect(getCurrentPageElement(wrapper2).exists()).toBeFalsy();

      const wrapper3 = getShallowWrapper({
        totalItems: -1
      });
      expect(getCurrentPageElement(wrapper3).exists()).toBeFalsy();
    });

    it("previous button should call onPageChange with the current page minus 1", () => {
      const mockOnPageChange = jest.fn();
      const wrapper = getShallowWrapper({
        page: 5,
        onPageChange: mockOnPageChange
      });
      const button = getPrevButton(wrapper);
      button.simulate("click");
      expect(mockOnPageChange).toHaveBeenCalledWith(4);
    });

    it("next button should call onPageChange with the current page plus 1", () => {
      const mockOnPageChange = jest.fn();
      const wrapper = getShallowWrapper({
        page: 5,
        onPageChange: mockOnPageChange
      });
      const button = getNextButton(wrapper);
      button.simulate("click");
      expect(mockOnPageChange).toHaveBeenCalledWith(6);
    });
  });

  describe("when can't go back and can't go forward", () => {
    it("should not render any button", () => {
      mockCanGoBack(false);
      mockCanGoForward(false);
      const wrapper = getShallowWrapper({});
      expect(getPrevButton(wrapper).exists()).toBeFalsy();
      expect(getNextButton(wrapper).exists()).toBeFalsy();
    });

    it("prev button should not call onPageChange if can't go back", () => {
      const mockOnPageChange = jest.fn();
      mockCanGoBack(true); // set to true so that the button is visible and we can get access to it, to call the handlePageChange
      const wrapper = getShallowWrapper({
        onPageChange: mockOnPageChange
      });
      mockCanGoBack(false); // set back to false, to test the appropriate behavior in handlePageChange
      const button = getPrevButton(wrapper);
      button.simulate("click");
      expect(mockOnPageChange).not.toHaveBeenCalled();
    });

    it("next button should not call onPageChange if can't go forward", () => {
      const mockOnPageChange = jest.fn();
      mockCanGoForward(true); // set to true so that the button is visible and we can get access to it, to call the handlePageChange
      const wrapper = getShallowWrapper({
        onPageChange: mockOnPageChange
      });
      mockCanGoForward(false); // set back to false, to test the appropriate behavior in handlePageChange
      const button = getNextButton(wrapper);
      button.simulate("click");
      expect(mockOnPageChange).not.toHaveBeenCalled();
    });
  });
});