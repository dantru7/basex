package org.basex.query.func.request;

import org.basex.query.*;
import org.basex.query.value.item.*;
import org.basex.query.value.seq.*;
import org.basex.util.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-19, BSD License
 * @author Christian Gruen
 */
public final class RequestAttribute extends RequestFn {
  @Override
  public Item item(final QueryContext qc, final InputInfo ii) throws QueryException {
    final String name = Token.string(toToken(exprs[0], qc));
    final Object value = request(qc).getAttribute(name);
    return value == null ? Empty.VALUE : Str.get(value.toString());
  }
}
