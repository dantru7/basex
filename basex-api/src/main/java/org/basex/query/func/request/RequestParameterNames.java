package org.basex.query.func.request;

import static org.basex.query.QueryError.*;

import java.io.*;

import org.basex.http.*;
import org.basex.query.*;
import org.basex.query.value.*;
import org.basex.query.value.seq.*;
import org.basex.util.hash.*;
import org.basex.util.list.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-19, BSD License
 * @author Christian Gruen
 */
public final class RequestParameterNames extends RequestFn {
  @Override
  public Value value(final QueryContext qc) throws QueryException {
    final HTTPParams hp = new HTTPParams(request(qc));
    try {
      final TokenSet cache = new TokenSet();
      for(final String name : hp.query().keySet()) cache.add(name);
      for(final String name : hp.form(qc.context.options).keySet()) cache.add(name);
      final TokenList names = new TokenList(cache.size());
      for(final byte[] name : cache) names.add(name);
      return StrSeq.get(names);
    } catch(final IOException ex) {
      throw REQUEST_PARAMETER.get(info, hp.queryString());
    }
  }
}