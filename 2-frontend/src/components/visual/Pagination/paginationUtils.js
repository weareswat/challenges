import { isNil } from "ramda";

/**
 * Returns a boolean, to indicate if we can go back 1 page, from the current one
 * @param {Number} page - current page 
 */
export const canGoBack = page => {
  if (isNil(page)) {
    return false;
  }
  return page - 1 >= 0;
};

/**
 * Returns a boolean, to indicate if we can go forward 1 page, from the current one
 * @param {Number} page - current page
 * @param {Number} totalItems - total number of items to paginate
 * @param {Number} offset - number of items per page
 */
export const canGoForward = (page, totalItems, offset) => {
  if (isNil(page) || isNil(totalItems) || isNil(offset) || offset <= 0) {
    return false;
  }
  return (page + 1) < (totalItems / offset);
};